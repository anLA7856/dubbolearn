package com.anla.rpc.conditions.pureapi.test;

import org.apache.dubbo.common.utils.ConcurrentHashSet;

import java.util.Set;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/8/28 9:58
 **/
public class TestSetFrequence {
    public static void main(String[] args) {
        Set<String> names = new ConcurrentHashSet<>();
        names.add("dog");
        names.add("cat");
        names.add("mouse");
        for (String s : names){
            System.out.println(s);
        }
    }
}
