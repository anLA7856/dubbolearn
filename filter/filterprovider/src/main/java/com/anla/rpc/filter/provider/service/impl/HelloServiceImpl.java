package com.anla.rpc.filter.provider.service.impl;

import com.anla.rpc.filter.provider.service.HelloService;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.dubbo.rpc.RpcContext.getContext;

/**
 * @author anLA7856
 * @date 19-7-18 下午11:38
 * @description
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name +
                ", request from consumer: " + getContext().getRemoteAddress());
        return "Hello " + name + ", response from provider: " + getContext().getLocalAddress();

    }
}
