package com.anla.rpc.filter.consumer;

import com.anla.rpc.filter.provider.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author anLA7856
 * @date 19-7-18 下午11:41
 * @description
 */
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer.xml");
        context.start();
        HelloService demoService = (HelloService) context.getBean("helloService");
        String hello = demoService.hello("anla7856");
        System.out.println(hello);
    }
}
