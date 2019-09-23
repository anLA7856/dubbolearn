package com.anla.rpc.conditions.callback.provider.service;

/**
 * @author anLA7856
 * @date 19-7-18 下午10:09
 * @description
 */
public interface CallbackListener {
    /**
     * 理解为方法，就是一个方法
     * @param msg
     */
    void changed(String msg);
}
