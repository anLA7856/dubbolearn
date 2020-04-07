package com.anla.rpc.configcenter.consumer.aaconfig;

import com.anla.rpc.configcenter.provider.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author luoan
 * @version 1.0
 * @date 2020/3/28 13:30
 **/
 @Configuration
public class MyPriorityReferenceConfig {

    @Target({ ANNOTATION_TYPE, FIELD, TYPE })
    @Retention(RUNTIME)
    @Reference(version = "1.0.0", check = false)
    @interface Test3 {
        String tset3() default "test3";
    }

    @Test3
    HelloService helloService;
}
