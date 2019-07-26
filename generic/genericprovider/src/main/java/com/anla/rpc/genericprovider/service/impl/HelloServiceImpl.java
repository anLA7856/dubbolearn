package com.anla.rpc.genericprovider.service.impl;

import com.anla.rpc.genericprovider.service.HelloService;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/26 17:07
 **/
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "hello, " + name +" I am anla7856";
    }
}
