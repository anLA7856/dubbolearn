package com.anla.rpc.pureapi.provider;

import com.anla.rpc.pureapi.service.HelloService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/6/13 17:24
 **/
public class ProviderApplication {
    public static void main(String[] args) throws IOException {
        ServiceConfig<HelloService> service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("dubbo-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        service.setInterface(HelloService.class);
        service.setRef(new HelloServiceImpl());
        service.export();
        System.out.println("first-dubbo-provider is running.");
        System.in.read();
    }
}
