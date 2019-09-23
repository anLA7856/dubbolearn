package com.anla.rpc.conditions.metrics.provider.service.impl;

import com.anla.rpc.conditions.metrics.provider.service.HelloService;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/29 16:51
 **/
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello, " + name +". there I will teach you using dubbo metrics";
    }
}
