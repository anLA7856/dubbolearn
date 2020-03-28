package com.anla.rpc.configcenter.consumer.adubbo;

import com.anla.rpc.configcenter.provider.service.HelloService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author anLA7856
 * @date 19-7-22 下午10:38
 * @description
 */
@Component("helloDubbo")
public class HelloDubbo {


    HelloService helloService;
    @Resource
    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name){
        return helloService.hello(name);
    }
}
