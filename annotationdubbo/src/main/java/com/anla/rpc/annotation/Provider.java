package com.anla.rpc.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * 用于启动项目
 * @author anLA7856
 * @date 19-7-15 下午10:52
 * @description
 */
public class Provider {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        context.start();
        System.out.println("dubbo service started.");
        new CountDownLatch(1).await();
    }
}
