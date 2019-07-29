package com.anla.rpc.notify.consumer.api.impl;

import com.anla.rpc.notify.consumer.api.NotifyService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author anLA7856
 * @date 19-7-29 下午11:17
 * @description
 */
public class NotifyServiceImpl implements NotifyService {

    public Map<Integer, Object> ret = new HashMap<>();

    @Override
    public void onReturn(String name, int id) {
        ret.put(id, name);
        System.out.println("onReturn Hello name: " + name + ", id:" +id);
    }

    @Override
    public void onThrow(Throwable ex, int id) {
        ret.put(id, ex);
        System.out.println("onThrow Hello ex: " + ex + ", id:" +id);
    }
}
