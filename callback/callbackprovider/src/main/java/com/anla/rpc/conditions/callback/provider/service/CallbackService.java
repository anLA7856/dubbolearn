package com.anla.rpc.conditions.callback.provider.service;

/**
 * @author anLA7856
 * @date 19-7-18 下午10:10
 * @description
 */
public interface CallbackService {

    /**
     * 这个 索引为1的是callback类型。
     * dubbo 将基于长连接生成反向代理，就可以在服务端调用客户端逻辑
     * @param key
     * @param listener
     */
    void addListener(String key, CallbackListener listener);
}
