package com.anla.rpc.asyncprovider.service.iml;

import com.anla.rpc.asyncprovider.service.HelloService;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/17 17:54
 **/
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello, Ms." + name;
    }
}
