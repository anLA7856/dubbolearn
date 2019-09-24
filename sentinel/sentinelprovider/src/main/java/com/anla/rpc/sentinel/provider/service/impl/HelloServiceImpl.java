package com.anla.rpc.sentinel.provider.service.impl;

import com.anla.rpc.sentinel.provider.service.HelloService;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/30 18:08
 **/
public class HelloServiceImpl implements HelloService {
    @Override
    public String helloCat(String name) {
        return "hello Cat? are you? " + name;
    }

    @Override
    public String helloDog(String name) {
        return "Oh, you are miss dog? " + name;
    }
}
