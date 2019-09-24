package com.anla.rpc.annotation.dubbo.service.impl;

import com.anla.rpc.annotation.dubbo.service.GoodByeService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author anLA7856
 * @date 19-7-15 下午11:05
 * @description
 */
@Service(version = "1.0.0")
public class GoodByeServiceImpl implements GoodByeService {
    @Override
    public String sayGoodBye(String name) {
        return "good bye " + name + ", remember me";
    }
}
