package com.anla.rpc.notify.provider.service.impl;

import com.anla.rpc.notify.provider.service.HelloService;

/**
 * @author anLA7856
 * @date 19-7-29 下午11:11
 * @description
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(Integer name) {
        // 有错误才会被onReturn 和 onThrow执行
        if (name == 20) {
            throw new RuntimeException("this is exception");
        }
        return "Hello " + name + ", I am the hello service.";
    }
}
