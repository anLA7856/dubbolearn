package com.anla.rpc.multiprotocol.provider.service.impl;

import com.anla.rpc.multiprotocol.provider.service.HelloHessianService;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/24 10:45
 **/
public class HelloHessanServiceImpl implements HelloHessianService {
    @Override
    public String hello(String name) {
        return "Hello " + name + ", I am anLA7856, while I use hessian protocol ";
    }
}
