package com.anla.rpc.conditions.mock.consumer;

import com.anla.rpc.conditions.mock.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/29 18:22
 **/
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer.xml");
        context.start();
        HelloService helloService = context.getBean("helloService", HelloService.class);
        String hello = helloService.hello("world");
        System.out.println("result: " + hello);
    }
}
