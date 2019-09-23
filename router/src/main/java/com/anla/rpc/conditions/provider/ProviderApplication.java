package com.anla.rpc.conditions.provider;

import com.anla.rpc.conditions.service.HelloService;
import com.anla.rpc.conditions.service.HelloService2;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
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
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-provider");

        ServiceConfig<HelloService> service = new ServiceConfig<>();
        service.setApplication(applicationConfig);
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");
        int port = Integer.valueOf(args[0]);
        System.out.println(port);
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(port);
        service.setProtocol(protocolConfig);
        service.setRegistry(registryConfig);
        service.setInterface(HelloService.class);
        service.setRef(new HelloServiceImpl());
        service.export();


        ServiceConfig<HelloService2> service2 = new ServiceConfig<>();
        service2.setApplication(applicationConfig);
        service2.setProtocol(protocolConfig);
        service2.setRegistry(registryConfig);
        service2.setInterface(HelloService2.class);
        service2.setRef(new HelloService2Impl());
        service2.export();
        System.out.println("first-dubbo-provider is running.");
        System.in.read();
    }
}
