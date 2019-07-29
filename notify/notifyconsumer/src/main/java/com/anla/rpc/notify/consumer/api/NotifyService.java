package com.anla.rpc.notify.consumer.api;

/**
 * @author anLA7856
 * @date 19-7-29 下午11:17
 * @description
 */
public interface NotifyService {
    void onReturn(String name, int id);

    void onThrow(Throwable ex, int id);
}
