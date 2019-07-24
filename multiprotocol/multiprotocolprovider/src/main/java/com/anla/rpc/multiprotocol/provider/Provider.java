package com.anla.rpc.multiprotocol.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @author anLA7856
 * @date 19-7-23 下午11:48
 * @description
 */
public class Provider {
    public static void main(String[] args) throws Exception {
//        ZKTools.generateDubboProperties();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/provider.xml");
        context.start();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
