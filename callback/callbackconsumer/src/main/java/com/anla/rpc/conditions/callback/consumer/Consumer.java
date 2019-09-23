package com.anla.rpc.conditions.callback.consumer;

import com.anla.rpc.conditions.callback.provider.service.CallbackService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author anLA7856
 * @date 19-7-18 下午10:20
 * @description
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer.xml");
        context.start();

        CallbackService callbackService = context.getBean("callbackService", CallbackService.class);
        // 增加listener
        callbackService.addListener("foo.bar", msg -> System.out.println("callback:" + msg));
    }
}
