package com.anla.rpc.hystrix.provider.service.impl;

import com.anla.rpc.hystrix.provider.service.HelloService;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/8/2 14:26
 **/
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello Hystrix? " + name;
    }
}
