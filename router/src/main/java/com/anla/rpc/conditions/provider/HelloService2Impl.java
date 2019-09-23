package com.anla.rpc.conditions.provider;

import com.anla.rpc.conditions.service.HelloService2;
import org.apache.dubbo.rpc.RpcContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author anLA7856
 * @date 19-9-23 下午10:09
 * @description
 */
public class HelloService2Impl implements HelloService2 {
    private static AtomicInteger count = new AtomicInteger(0);
    @Override
    public String hello(String name) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] HelloService2 " + name +
                ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "HelloService2 " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress() +
                ", count: " + count.incrementAndGet();
    }
}
