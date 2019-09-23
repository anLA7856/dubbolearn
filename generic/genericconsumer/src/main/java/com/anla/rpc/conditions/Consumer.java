package com.anla.rpc.conditions;

import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/26 17:13
 **/
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer.xml");
        context.start();
        GenericService genericService = (GenericService) context.getBean("helloService");
        Object result = genericService.$invoke("hello", new String[]{"java.lang.String"}, new Object[]{"world"});
        System.err.println("invokeSayHello(return): " + result);
    }
}
