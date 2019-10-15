package com.anla.rpc.filter.consumer;

import com.anla.rpc.filter.provider.api.Dog;
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
        HelloService helloService = (HelloService) context.getBean("helloService");
        String hello = helloService.hello("anla7856");
        System.out.println(hello);
        Dog dog = helloService.getDog(1);
        System.out.println(dog);
//        Dog badDog = helloService.getDog(0);
//        System.out.println(badDog);
    }
}
