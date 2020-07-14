package com.anla.springcloud.demo.provider;

import org.apache.dubbo.config.annotation.Service;

/**
 * @author luoan
 * @version 1.0
 * @date 2020/7/14 15:56
 **/
@Service(version = "1.0.0")
public class HelloAPI implements HelloProvider{
    public String hello(){
        return "hello";
    }
}
