package com.anla.rpc.conditions.annotation.api;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author anLA7856
 * @date 19-7-15 下午10:52
 * @description
 */
public class Consumer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        final HelloFacadeAPI helloFacadeAPI = (HelloFacadeAPI) context.getBean("helloFacadeAPI");

        System.out.println("hello :" + helloFacadeAPI.hello("alita"));
        System.out.println("goodbye :" + helloFacadeAPI.goodbye("alita"));
    }
}
