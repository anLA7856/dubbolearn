package com.anla.rpc.mergerprovider1.impl;

import com.anla.rpc.mergerapi.HelloService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/9/23 14:00
 **/
public class HelloServiceImpl2 implements HelloService {
    @Override
    public List<String> mergeResult() {
        List<String> menus = new ArrayList<>();
        menus.add("HelloServiceImpl-2.1");
        menus.add("HelloServiceImpl-2.2");
        return menus;
    }
}
