package com.anla.rpc.consumer;

import com.anla.rpc.service.HelloService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/6/13 17:19
 **/
public class ConsumerApplication {

    public static void main(String[] args) {
        ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-consumer");
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");
        reference.setApplication(applicationConfig);
        reference.setRegistry(registryConfig);
        reference.setInterface(HelloService.class);
        HelloService service = reference.get();
        String message = service.hello("router1");
        System.out.println(message);
        String message2 = service.hello2("router2");
        System.out.println(message2);


//        ReferenceConfig<HelloService2> reference2 = new ReferenceConfig<>();
//        reference2.setApplication(applicationConfig);
//        reference2.setRegistry(registryConfig);
//        reference2.setInterface(HelloService2.class);
//        HelloService2 service2 = reference2.get();
//        String message2 = service2.hello("router2");
//        System.out.println(message2);
    }
}
