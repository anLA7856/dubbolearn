package com.anla.rpc.conditions.pureapi.provider;

import com.anla.rpc.conditions.pureapi.service.HelloService;
import org.apache.dubbo.config.*;

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
        System.out.println("first-dubbo-provider is running.");
        System.in.read();
    }
}
