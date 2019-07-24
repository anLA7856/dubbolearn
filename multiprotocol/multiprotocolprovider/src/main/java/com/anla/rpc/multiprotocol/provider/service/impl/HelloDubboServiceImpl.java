package com.anla.rpc.multiprotocol.provider.service.impl;

import com.anla.rpc.multiprotocol.provider.service.HelloDubboService;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/24 10:44
 **/
public class HelloDubboServiceImpl implements HelloDubboService {
    @Override
    public String hello(String name) {
        return "Hello " + name + ", I am anLA7856, I use dubbo protocol ";
    }
}
