package com.anla.rpc.groupconsumer;

import com.anla.rpc.groupprovider.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author anLA7856
 * @date 19-7-28 下午3:57
 * @description
 */
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer.xml");
        context.start();

        HelloService helloCatService = context.getBean("helloCatService", HelloService.class);
        HelloService helloDogService = context.getBean("helloDogService", HelloService.class);

        String resultGroupCat = helloCatService.hello("miamo");
        System.out.println(resultGroupCat);

        String resultGroupDog = helloDogService.hello("miamo");
        System.out.println(resultGroupDog);
    }
}
