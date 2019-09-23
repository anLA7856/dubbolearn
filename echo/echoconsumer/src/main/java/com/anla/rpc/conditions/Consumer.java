package com.anla.rpc.conditions;

import com.anla.rpc.conditions.echoprovider.service.HelloService;
import org.apache.dubbo.rpc.service.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author anLA7856
 * @date 19-7-24 下午10:26
 * @description
 */
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer.xml");
        context.start();
        HelloService helloService = context.getBean("helloService", HelloService.class);

        EchoService echoService = (EchoService) helloService;
        String status = (String) echoService.$echo("OK");
        System.out.println("echo result: " + status);
    }
}
