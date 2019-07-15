package com.anla.rpc.annotation.service.impl;

import com.anla.rpc.annotation.service.HelloService;

/**
 * @author anLA7856
 * @date 19-7-15 下午11:05
 * @description
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "hello " + name + ", I am anla7856";
    }
}
