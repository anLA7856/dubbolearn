package com.anla.rpc.conditions.filter.provider.service.impl;

import com.anla.rpc.conditions.filter.provider.api.Dog;
import com.anla.rpc.conditions.filter.provider.service.HelloService;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.dubbo.rpc.RpcContext.getContext;

/**
 * @author anLA7856
 * @date 19-7-18 下午11:38
 * @description
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name +
                ", request from consumer: " + getContext().getRemoteAddress());
        return "Hello " + name + ", response from provider: " + getContext().getLocalAddress();

    }

    @Override
    public Dog getDog(int id) {
        if (id == 0){
            throw new IllegalArgumentException("id is 0");
        }
        Dog dog = new Dog();
        dog.setName("hello dog");
        return dog;
    }
}
