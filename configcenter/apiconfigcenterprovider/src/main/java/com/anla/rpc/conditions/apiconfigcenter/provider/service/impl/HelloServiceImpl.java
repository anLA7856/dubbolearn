package com.anla.rpc.conditions.apiconfigcenter.provider.service.impl;

import com.anla.rpc.conditions.apiconfigcenter.provider.service.HelloService;

/**
 * @author anLA7856
 * @date 19-7-22 下午11:39
 * @description
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello , I am api config center: " + name;
    }
}
