package com.anla.rpc.configcenter.consumer.aaconfig;

import com.anla.rpc.configcenter.provider.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.Configuration;

/**
 * @author luoan
 * @version 1.0
 * @date 2020/3/28 13:30
 **/
@Configuration
public class MyPriorityReferenceConfig {
    @Reference(version = "1.0.0", check = false)
    HelloService helloService;
}
