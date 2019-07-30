package com.anla.rpc.sentinel.consumer;

import com.alibaba.csp.sentinel.slots.block.SentinelRpcException;
import com.anla.rpc.sentinel.provider.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/30 18:14
 **/
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer.xml");
        context.start();

        HelloService helloService = context.getBean("helloService", HelloService.class);

        for (int i = 0; i < 15; i++) {
            try {
                String messageCat = helloService.helloCat("dubbo");
                System.out.println("Success: " + messageCat);
                String messageDog = helloService.helloDog("dubbo");
                System.out.println("Success: " + messageDog);
            } catch (SentinelRpcException ex) {
                System.out.println("Blocked");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
