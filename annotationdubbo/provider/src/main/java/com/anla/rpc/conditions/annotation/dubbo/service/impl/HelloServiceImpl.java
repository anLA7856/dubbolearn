package com.anla.rpc.conditions.annotation.dubbo.service.impl;

import com.anla.rpc.conditions.annotation.dubbo.service.HelloService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author anLA7856
 * @date 19-7-15 下午11:05
 * @description
 */
@Service(version = "1.0.0")
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "hello " + name + ", I am anla7856";
    }
}
