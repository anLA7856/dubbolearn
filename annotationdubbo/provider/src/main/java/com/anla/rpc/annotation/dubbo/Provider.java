package com.anla.rpc.annotation.dubbo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/16 16:43
 **/
public class Provider {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        context.start();
        System.out.println("dubbo service started.");
        new CountDownLatch(1).await();
    }
}