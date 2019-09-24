package com.anla.rpc.groupprovider.service.impl;

import com.anla.rpc.groupprovider.service.HelloService;

/**
 * @author anLA7856
 * @date 19-7-28 下午3:50
 * @description
 */
public class HelloDogService implements HelloService {
    @Override
    public String hello(String name) {
        return "hello anLA7856, I am "+ name + ". This is my dog";
    }
}
