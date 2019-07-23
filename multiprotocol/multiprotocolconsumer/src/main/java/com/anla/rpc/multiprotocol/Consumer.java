package com.anla.rpc.multiprotocol;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author anLA7856
 * @date 19-7-23 下午11:46
 * @description
 */
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/configcenter-consumer.xml");
        context.start();

        DemoService demoService = (DemoService) context.getBean("demoService");

        String hello = demoService.sayHello("world");
        System.out.println(hello);
    }
}
