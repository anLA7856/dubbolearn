package com.anla.rpc.conditions.mock.service;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/29 18:18
 **/
public class HelloServiceMock implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello " + name +", I am mock, are you?";
    }
}
