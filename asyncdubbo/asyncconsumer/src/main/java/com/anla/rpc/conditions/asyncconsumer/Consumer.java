package com.anla.rpc.conditions.asyncconsumer;

import com.anla.rpc.conditions.asyncprovider.service.HelloService;
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
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer.xml");
        context.start();

        // 第一种
        HelloService helloService = context.getBean("helloService", HelloService.class);
        helloService.hello("anla7856");
        CompletableFuture<String> helloFuture = RpcContext.getContext().getCompletableFuture();
        helloFuture.whenComplete((retValue, exception) -> {
            if (exception == null) {
                System.out.println("return value: " + retValue);
            } else {
                exception.printStackTrace();
            }
        });

        // 第二种
        CompletableFuture<String> future = RpcContext.getContext().asyncCall(() -> helloService.hello("async call request"));
        try {
            System.out.println("async call returned: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 第三种
        RpcContext.getContext().asyncCall(() -> {
            helloService.hello("one way call request1");
        });

        // 第四种，使用 AsyncContext 来获取数据
        RpcContext.getContext().setAttachment("consumer-key1", "consumer-value1");
        HelloService asyncContextHelloService = context.getBean("asyncContextHelloService", HelloService.class);

        System.out.println(asyncContextHelloService.hello("async call request"));

        // 使用 CompletableFuture 包装返回值
        CompletableFuture<String> completableFuture = helloService.greeting("async call request", (byte) 1);
        System.out.println("async call returned: " + completableFuture.get());
    }
}
