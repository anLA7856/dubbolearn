package com.anla.rpc.configcenter.consumer.aaconfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * use for autowired ReferenceBean
 * 1. 存储所有的有reference的注解的类。
 * 2. 如果有用到@Autowired 或者 @Resource 进行注入，则先将其中一个bean进行get操作。
 * @author anLA7856
 * @date 20-3-29 下午3:07
 * @description
 */
@Configuration
public class ReferenceBeanFactoryPostProcessor implements BeanFactoryPostProcessor , MergedBeanDefinitionPostProcessor, BeanFactoryAware {

    protected final Log logger = LogFactory.getLog(getClass());

    private final Set<Class<? extends Annotation>> referenceAnnotationTypes = new LinkedHashSet<>(4);
    private final Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(4);

    private Map<String, Set<String>> referenceIdToBeanNames = new ConcurrentHashMap<>(256);
    private Map<String, Boolean> referenceHasInit = new ConcurrentHashMap<>(256);

    @NonNull
    private BeanFactory beanFactory;

    public ReferenceBeanFactoryPostProcessor() {
        this.referenceAnnotationTypes.add(Reference.class);
        this.autowiredAnnotationTypes.add(Autowired.class);
        this.autowiredAnnotationTypes.add(Resource.class);
        try {
            this.autowiredAnnotationTypes.add((Class<? extends Annotation>)
                    ClassUtils.forName("javax.inject.Inject", ReferenceBeanFactoryPostProcessor.class.getClassLoader()));
            logger.trace("JSR-330 'javax.inject.Inject' annotation found and supported for autowiring");
        }
        catch (ClassNotFoundException ex) {
            // JSR-330 API not available - simply skip.
        }
    }

    private void buildReferenceMetadata(final Class<?> clazz, String beanName) {
        findOrInjectAnnotation(clazz, (FindCallback) this::cacheRef, beanName, referenceAnnotationTypes, Reference.class.getName());
    }

    private void findOrInjectAnnotation(Class<?> targetClass, CallBack callBack, String beanName, Set<Class<? extends Annotation>> annotationTypes, String type) {
        do {
            ReflectionUtils.doWithLocalFields(targetClass, field -> {
                AnnotationAttributes ann = findAnnotation(field, annotationTypes);
                if (ann != null) {
                    if (Modifier.isStatic(field.getModifiers())) {
                        if (logger.isInfoEnabled()) {
                            logger.info(type + " is not supported on static fields: " + field);
                        }
                        return;
                    }
                    String name = field.getType().getName();
                    callBack.callback(name, beanName);
                }
            });

            Class<?> finalTargetClass = targetClass;
            ReflectionUtils.doWithLocalMethods(targetClass, method -> {
                Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(method);
                if (!BridgeMethodResolver.isVisibilityBridgeMethodPair(method, bridgedMethod)) {
                    return;
                }
                AnnotationAttributes ann = findAnnotation(bridgedMethod, annotationTypes);
                if (ann != null && method.equals(ClassUtils.getMostSpecificMethod(method, finalTargetClass))) {
                    if (Modifier.isStatic(method.getModifiers())) {
                        if (logger.isInfoEnabled()) {
                            logger.info(type + " annotation is not supported on static methods: " + method);
                        }
                        return;
                    }
                    if (method.getParameterCount() == 0) {
                        if (logger.isInfoEnabled()) {
                            logger.info(type + " annotation should only be used on methods with parameters: " +
                                    method);
                        }
                    }
                    if (callBack instanceof FindCallback){
                        String name = method.getReturnType().getName();
                        callBack.callback(name, beanName);
                    }else {
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        for (Class<?> clazz : parameterTypes){
                            String name = clazz.getName();
                            callBack.callback(name, beanName);
                        }
                    }

                }
            });

            targetClass = targetClass.getSuperclass();
        }
        while (targetClass != null && targetClass != Object.class);
    }

    private void cacheRef(String name, String beanName) {
        Set<String> beanNames = referenceIdToBeanNames.get(name);
        if (beanNames == null){
            beanNames = new HashSet<>();
            referenceHasInit.put(name, Boolean.FALSE);
        }
        beanNames.add(beanName);
        referenceIdToBeanNames.put(name, beanNames);
    }

    @Nullable
    private AnnotationAttributes findAnnotation(AccessibleObject ao, Set<Class<? extends Annotation>> autowiredAnnotationTypes) {
        if (ao.getAnnotations().length > 0) {
            for (Class<? extends Annotation> type : autowiredAnnotationTypes) {
                // same effects with AnnotatedElementUtils.getMergedAnnotation
                AnnotationAttributes attributes = AnnotatedElementUtils.getMergedAnnotationAttributes(ao, type);
                if (attributes != null) {
                    return attributes;
                }
            }
        }
        return null;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for(String beanName : beanNames){
            Class<?> clazz = beanFactory.getType(beanName);
            if (clazz != null){
                buildReferenceMetadata(clazz, beanName);
            }else {
                logger.debug(beanName + " class not found");
            }
        }
    }


    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        initReference(beanType, beanName);
    }

    private void initReference(Class<?> beanType, String beanName) {
        findOrInjectAnnotation(beanType, (InjectCallback) this::doInitReference, beanName, this.autowiredAnnotationTypes, Autowired.class.getName());
    }

    private void doInitReference(String name, String beanName) {
        if (this.referenceIdToBeanNames.containsKey(name) && !referenceHasInit.get(name)){
            Set<String> beanNames = referenceIdToBeanNames.get(name);
            beanNames.forEach(t->beanFactory.getBean(t));
            referenceHasInit.put(name, Boolean.TRUE);
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (!(beanFactory instanceof ConfigurableListableBeanFactory)) {
            throw new IllegalArgumentException(
                    "ReferenceBeanFactoryPostProcessor requires a ConfigurableListableBeanFactory: " + beanFactory);
        }
        this.beanFactory = beanFactory;
    }

    interface CallBack{
        void callback(String name, String beanName);
    }

    interface FindCallback extends CallBack{

    }
    interface InjectCallback extends CallBack{

    }
}
