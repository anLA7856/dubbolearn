package com.anla.rpc.hystrix.consumer;

import com.anla.rpc.hystrix.provider.service.HelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/8/5 15:41
 **/
@SpringBootApplication
@Service
@EnableHystrix
public class Consumer {
    @Reference(version = "1.0.0")
    private HelloService demoService;

    public static void main(String[] args) {



        ConfigurableApplicationContext context = SpringApplication.run(Consumer.class, args);
        Consumer application = context.getBean(Consumer.class);

        String result = application.doSayHello("world");
        System.out.println("result: " + result);
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public String doSayHello(String name) {
        return demoService.hello(name);
    }

    public String reliable(String name) {
        return "hystrix fallback value";
    }
}
