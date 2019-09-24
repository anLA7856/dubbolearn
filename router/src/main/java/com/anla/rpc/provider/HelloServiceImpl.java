package com.anla.rpc.provider;

import com.anla.rpc.service.HelloService;
import org.apache.dubbo.rpc.RpcContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/6/13 17:25
 **/
public class HelloServiceImpl implements HelloService {
    private static AtomicInteger count = new AtomicInteger(0);
    private static AtomicInteger count2 = new AtomicInteger(0);
    @Override
    public String hello(String name) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] hello " + name +
                ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "hello " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress() +
                ", count: " + count.incrementAndGet();
    }

    @Override
    public String hello2(String name) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] hello2 " + name +
                ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "hello2 " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress() +
                ", count: " + count2.incrementAndGet();
    }
}
