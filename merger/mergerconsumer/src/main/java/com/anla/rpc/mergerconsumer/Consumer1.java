package com.anla.rpc.mergerconsumer;

import com.anla.rpc.mergerapi.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/9/23 12:54
 **/
public class Consumer1 {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer1.xml");
        context.start();

        HelloService helloService = (HelloService) context.getBean("helloService");
        System.out.println(helloService.mergeResult());
    }

}
