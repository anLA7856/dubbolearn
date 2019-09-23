package com.anla.rpc.conditions.container.jetty.service.impl;

import com.anla.rpc.conditions.container.jetty.JettyContainer;
import com.anla.rpc.conditions.container.jetty.HelloHandler;
import com.anla.rpc.conditions.container.jetty.service.HelloService;

/**
 * @author anLA7856
 * @date 19-7-28 下午8:41
 * @description
 */
public class HelloServiceImpl implements HelloService {

    private static JettyContainer container = new JettyContainer();

    @Override
    public String hello(String name) {
        container.setServerHandler(new HelloHandler());

        container.start();

        container.stop();
        return null;
    }
}
