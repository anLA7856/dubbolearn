package com.anla.rpc.configcenter.consumer.aaconfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.StandardMethodMetadata;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
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

    @Nullable
    private BeanFactory beanFactory;

    public ReferenceBeanFactoryPostProcessor() {
        this.referenceAnnotationTypes.add(Reference.class);
        this.autowiredAnnotationTypes.add(Autowired.class);
        this.autowiredAnnotationTypes.add(Value.class);
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
        Class<?> targetClass = clazz;

        do {
            ReflectionUtils.doWithLocalFields(targetClass, field -> {
                AnnotationAttributes ann = findAnnotation(field, this.referenceAnnotationTypes);
                if (ann != null) {
                    if (Modifier.isStatic(field.getModifiers())) {
                        if (logger.isInfoEnabled()) {
                            logger.info("Autowired annotation is not supported on static fields: " + field);
                        }
                        return;
                    }
                    String name = field.getType().getName();
                    Set<String> beanNames = referenceIdToBeanNames.get(name);
                    if (beanNames == null){
                        beanNames = new HashSet<>();
                        referenceHasInit.put(name, Boolean.FALSE);
                    }
                    beanNames.add(beanName);
                    referenceIdToBeanNames.put(name, beanNames);
                }
            });

            ReflectionUtils.doWithLocalMethods(targetClass, method -> {
                Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(method);
                if (!BridgeMethodResolver.isVisibilityBridgeMethodPair(method, bridgedMethod)) {
                    return;
                }
                AnnotationAttributes ann = findAnnotation(bridgedMethod, this.referenceAnnotationTypes);
                if (ann != null && method.equals(ClassUtils.getMostSpecificMethod(method, clazz))) {
                    if (Modifier.isStatic(method.getModifiers())) {
                        if (logger.isInfoEnabled()) {
                            logger.info("Autowired annotation is not supported on static methods: " + method);
                        }
                        return;
                    }
                    if (method.getParameterCount() == 0) {
                        if (logger.isInfoEnabled()) {
                            logger.info("Autowired annotation should only be used on methods with parameters: " +
                                    method);
                        }
                    }
                    String name = method.getReturnType().getName();
                    Set<String> beanNames = referenceIdToBeanNames.get(name);
                    if (beanNames == null){
                        beanNames = new HashSet<>();
                        referenceHasInit.put(name, Boolean.FALSE);
                    }
                    beanNames.add(beanName);
                    referenceIdToBeanNames.put(name, beanNames);
                }
            });

            targetClass = targetClass.getSuperclass();
        }
        while (targetClass != null && targetClass != Object.class);
    }

    @Nullable
    private AnnotationAttributes findAnnotation(AccessibleObject ao, Set<Class<? extends Annotation>> autowiredAnnotationTypes) {
        if (ao.getAnnotations().length > 0) {
            for (Class<? extends Annotation> type : autowiredAnnotationTypes) {
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
        for(String beanName : beanFactory.getBeanDefinitionNames()){
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            String beanClassName = beanDefinition.getBeanClassName();
            try {
                if(!StringUtils.isEmpty(beanClassName)){
                    Class clazz = Class.forName(beanClassName);
                    buildReferenceMetadata(clazz, beanName);
                }else {
                    if (!(beanDefinition instanceof RootBeanDefinition)){
                        logger.info("beanDefinition is  not a RootBeanDefinition");
                        continue;
                    }
                    RootBeanDefinition rootBeanDefinition = (RootBeanDefinition) beanDefinition;
                    Object source = rootBeanDefinition.getSource();
                    if (!(source instanceof MethodMetadata)){
                        logger.info("methodMetadata is null");
                        continue;
                    }
                    MethodMetadata methodMetadata = (MethodMetadata) source;
                    if(!methodMetadata.getReturnTypeName().equals(Void.TYPE.toString())){
                        Class clazz = Class.forName(methodMetadata.getReturnTypeName());
                        buildReferenceMetadata(clazz, beanName);
                    }else {
                        logger.info("method has no return type");
                    }
                }
            } catch (ClassNotFoundException e) {
                logger.info("beanClassName can not be loaded:" + beanClassName);
            }
        }
    }


    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        initReferenceIfNess(beanType);
    }

    private void initReferenceIfNess(Class<?> beanType) {
        Class<?> targetClass = beanType;

        do {
            ReflectionUtils.doWithLocalFields(targetClass, field -> {
                AnnotationAttributes ann = findAnnotation(field, this.autowiredAnnotationTypes);
                if (ann != null) {
                    if (Modifier.isStatic(field.getModifiers())) {
                        if (logger.isInfoEnabled()) {
                            logger.info("Autowired annotation is not supported on static fields: " + field);
                        }
                        return;
                    }
                    String name = field.getType().getName();
                    if (this.referenceIdToBeanNames.keySet().contains(name) && !referenceHasInit.get(name)){
                        Set<String> beanNames = referenceIdToBeanNames.get(name);
                        beanNames.forEach(t->beanFactory.getBean(t));
                        referenceHasInit.put(name, Boolean.TRUE);
                    }
                }
            });

            ReflectionUtils.doWithLocalMethods(targetClass, method -> {
                Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(method);
                if (!BridgeMethodResolver.isVisibilityBridgeMethodPair(method, bridgedMethod)) {
                    return;
                }
                AnnotationAttributes ann = findAnnotation(bridgedMethod,this.autowiredAnnotationTypes);
                if (ann != null && method.equals(ClassUtils.getMostSpecificMethod(method, beanType))) {
                    if (Modifier.isStatic(method.getModifiers())) {
                        if (logger.isInfoEnabled()) {
                            logger.info("Autowired annotation is not supported on static methods: " + method);
                        }
                        return;
                    }
                    if (method.getParameterCount() == 0) {
                        if (logger.isInfoEnabled()) {
                            logger.info("Autowired annotation should only be used on methods with parameters: " +
                                    method);
                        }
                    }
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    for (Class<?> clazz : parameterTypes){
                        String name = clazz.getName();
                        if (this.referenceIdToBeanNames.keySet().contains(name) && !referenceHasInit.get(name)){
                            Set<String> beanNames = referenceIdToBeanNames.get(name);
                            beanNames.forEach(t->beanFactory.getBean(t));
                            referenceHasInit.put(name, Boolean.TRUE);
                        }
                    }

                }
            });

            targetClass = targetClass.getSuperclass();
        }
        while (targetClass != null && targetClass != Object.class);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
