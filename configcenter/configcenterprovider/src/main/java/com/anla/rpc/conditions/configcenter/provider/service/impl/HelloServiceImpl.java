package com.anla.rpc.conditions.configcenter.provider.service.impl;

import com.anla.rpc.conditions.configcenter.provider.service.HelloService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author anLA7856
 * @date 19-7-22 下午10:49
 * @description
 */
@Service(version = "1.0.0")
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "hello " + name + ", I am anla7856";
    }
}
