package com.anla.rpc.pureapi.provider;

import com.anla.rpc.pureapi.service.HelloService;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/6/13 17:25
 **/
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "hello" + name;
    }
}
