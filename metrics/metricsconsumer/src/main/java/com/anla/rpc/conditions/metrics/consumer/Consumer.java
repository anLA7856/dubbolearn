package com.anla.rpc.conditions.metrics.consumer;

import com.anla.rpc.conditions.metrics.provider.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/29 15:10
 **/
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer.xml");
        context.start();

        HelloService helloService = context.getBean("helloService", HelloService.class);

        while (true) {
            try {
                Thread.sleep(3000);
                String hello = helloService.hello("world");
                System.out.println(hello);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
