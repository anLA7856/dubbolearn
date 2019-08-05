package com.anla.rpc.hystrix.provider.service.impl;

import com.anla.rpc.hystrix.provider.service.HelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/8/2 14:26
 **/
@Service(version = "1.0.0")
public class HelloServiceImpl implements HelloService {
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")})
    @Override
    public String hello(String name) {
        throw new RuntimeException("Exception to show hystrix enabled.");
    }
}
