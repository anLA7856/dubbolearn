package com.anla.rpc.conditions.spi.consumer;

import com.anla.rpc.conditions.spi.provider.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author anLA7856
 * @date 19-7-31 下午10:35
 * @description
 */
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer.xml");
        context.start();

        HelloService helloService = context.getBean("helloService", HelloService.class);

        String hello = helloService.hello("world");
        System.out.println(hello);
    }
}
