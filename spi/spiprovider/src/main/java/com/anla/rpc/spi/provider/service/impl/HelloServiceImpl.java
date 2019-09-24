package com.anla.rpc.spi.provider.service.impl;

import com.anla.rpc.spi.provider.service.HelloService;

/**
 * @author anLA7856
 * @date 19-7-30 下午11:51
 * @description
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello ? " + name + " This is spi";
    }
}
