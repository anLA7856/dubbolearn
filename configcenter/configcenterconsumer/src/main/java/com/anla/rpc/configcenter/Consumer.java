package com.anla.rpc.configcenter;

import com.anla.rpc.configcenter.config.ConsumerConfig;
import com.anla.rpc.configcenter.dubbo.HelloDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author anLA7856
 * @date 19-7-22 下午10:33
 * @description
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfig.class);
        context.start();
        final HelloDubbo helloDubbo = (HelloDubbo) context.getBean("helloDubbo");
        String hello = helloDubbo.hello("world");
        System.out.println("result :" + hello);
    }
}
