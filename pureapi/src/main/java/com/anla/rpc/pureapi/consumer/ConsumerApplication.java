package com.anla.rpc.pureapi.consumer;

import com.anla.rpc.pureapi.service.HelloService;
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
        reference.setApplication(new ApplicationConfig("dubbo-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        reference.setInterface(HelloService.class);
        HelloService service = reference.get();
        String message = service.hello("dubbo I am anla7856");
        System.out.println(message);
    }
}
