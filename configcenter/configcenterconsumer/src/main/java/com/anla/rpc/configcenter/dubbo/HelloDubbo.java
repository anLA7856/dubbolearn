package com.anla.rpc.configcenter.dubbo;

import com.anla.rpc.configcenter.provider.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * @author anLA7856
 * @date 19-7-22 下午10:38
 * @description
 */
@Component("helloDubbo")
public class HelloDubbo {

    @Reference(version = "1.0.0")
    HelloService  helloService;

    public String hello(String name){
        return helloService.hello(name);
    }
}
