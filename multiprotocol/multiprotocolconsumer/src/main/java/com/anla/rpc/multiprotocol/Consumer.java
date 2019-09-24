package com.anla.rpc.multiprotocol;

import com.anla.rpc.multiprotocol.provider.service.HelloDubboService;
import com.anla.rpc.multiprotocol.provider.service.HelloHessianService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author anLA7856
 * @date 19-7-23 下午11:46
 * @description
 */
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer.xml");
        context.start();

        HelloDubboService helloDubboService = (HelloDubboService) context.getBean("helloDubboService");
        HelloHessianService helloHessianService = (HelloHessianService) context.getBean("helloHessianService");
        System.out.println(helloDubboService.hello("world"));
        System.out.println(helloHessianService.hello("world"));
    }
}
