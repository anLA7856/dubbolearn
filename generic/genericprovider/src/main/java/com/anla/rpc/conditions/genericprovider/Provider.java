package com.anla.rpc.conditions.genericprovider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/26 15:32
 **/
public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/provider.xml");
        context.start();
        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
