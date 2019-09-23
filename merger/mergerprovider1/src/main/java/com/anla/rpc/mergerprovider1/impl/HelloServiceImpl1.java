package com.anla.rpc.mergerprovider1.impl;

import com.anla.rpc.mergerapi.HelloService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/9/23 13:59
 **/
public class HelloServiceImpl1 implements HelloService {
    @Override
    public List<String> mergeResult() {
        List<String> menus = new ArrayList<>();
        menus.add("HelloServiceImpl-1.1");
        menus.add("HelloServiceImpl-1.2");
        return menus;
    }

}
