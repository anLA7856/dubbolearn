package com.anla.rpc.configcenter.consumer.dubbo;

import com.anla.rpc.configcenter.provider.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.Configuration;

/**
 * @author luoan
 * @version 1.0
 * @date 2020/3/28 13:20
 **/
@Configuration
public class MyReferenceConfig {

    @Reference(version = "1.0.0", check = false)
    HelloService helloService;
}
