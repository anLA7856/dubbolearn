package com.anla.rpc.conditions.mock.service.impl;

import com.anla.rpc.conditions.mock.service.HelloService;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/29 18:17
 **/
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello " + name +", this is not mock";
    }
}
