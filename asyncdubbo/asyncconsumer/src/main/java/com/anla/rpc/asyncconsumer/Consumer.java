package com.anla.rpc.asyncconsumer;

import com.anla.rpc.asyncprovider.service.HelloService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 三种异步执行
 *
 * @author luoan
 * @version 1.0
 * @date 2019/7/17 17:43
 **/
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer.xml");
        context.start();

        // 第一种
        HelloService asyncService = context.getBean("asyncService", HelloService.class);
        asyncService.hello("anla7856");
        CompletableFuture<String> helloFuture = RpcContext.getContext().getCompletableFuture();
        helloFuture.whenComplete((retValue, exception) -> {
            if (exception == null) {
                System.out.println("return value: " + retValue);
            } else {
                exception.printStackTrace();
            }
        });

        // 第一种
        CompletableFuture<String> future = RpcContext.getContext().asyncCall(() -> asyncService.hello("async call request"));
        try {
            System.out.println("async call returned: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 第一种
        RpcContext.getContext().asyncCall(() -> {
            asyncService.hello("one way call request1");
        });
    }
}
