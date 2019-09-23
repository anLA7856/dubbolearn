package com.anla.rpc.conditions.mergerconsumer;

import com.anla.rpc.conditions.mergerapi.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/9/23 12:54
 **/
public class Consumer2 {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer2.xml");
        context.start();

        HelloService helloService = (HelloService) context.getBean("helloService");
        System.out.println(helloService.mergeResult());
    }

}
