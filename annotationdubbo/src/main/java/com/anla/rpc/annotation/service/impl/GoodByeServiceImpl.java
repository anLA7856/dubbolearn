package com.anla.rpc.annotation.service.impl;

import com.anla.rpc.annotation.service.GoodByeService;

/**
 * @author anLA7856
 * @date 19-7-15 下午11:05
 * @description
 */
public class GoodByeServiceImpl implements GoodByeService {
    @Override
    public String sayGoodBye(String name) {
        return "good bye " + name + ", remember me";
    }
}
