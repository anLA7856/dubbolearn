package com.anla.rpc.asyncprovider.service.iml;

import com.anla.rpc.asyncprovider.service.HelloService;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author anLA7856
 * @date 19-7-17 下午10:29
 * @description
 */
public class AsyncContextHelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        AsyncContext asyncContext = RpcContext.startAsync();
        System.out.println("hello , hello start");

        new Thread(() -> {
            asyncContext.signalContextSwitch();
            System.out.println("Attachment from consumer: " + RpcContext.getContext().getAttachment("consumer-key1"));
            System.out.println("async start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            asyncContext.write("Hello " + name + ", response from provider.");
            System.out.println("async end");
        }).start();
        System.out.println("hello end");
        return "hello, " + name;
    }
}
