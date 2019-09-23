package com.anla.rpc.conditions.hystrix.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import java.util.concurrent.CountDownLatch;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/8/2 14:23
 **/
@SpringBootApplication
@EnableHystrix
@EnableDubbo(scanBasePackages = {"com.anla.rpc.hystrix.provider.service.impl"})
public class Provider {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(Provider.class, args);

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
