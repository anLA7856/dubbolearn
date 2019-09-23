package com.anla.rpc.conditions.mergerprovider2.impl;

import com.anla.rpc.conditions.mergerapi.HelloService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/9/23 15:33
 **/
public class HelloServiceImpl3  implements HelloService {

    @Override
    public List<String> mergeResult() {
        List<String> menus = new ArrayList<>();
        menus.add("HelloServiceImpl-3.1");
        menus.add("HelloServiceImpl-3.2");
        return menus;
    }
}
