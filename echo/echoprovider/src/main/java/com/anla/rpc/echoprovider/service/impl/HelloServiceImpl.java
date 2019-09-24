package com.anla.rpc.echoprovider.service.impl;

import com.anla.rpc.echoprovider.service.HelloService;

/**
 * @author anLA7856
 * @date 19-7-24 下午10:31
 * @description
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String hello) {
        return "hello " + hello +", I am anLA7856";
    }
}
