package com.anla.rpc.conditions.callback.provider.impl;

import com.anla.rpc.conditions.callback.provider.service.CallbackListener;
import com.anla.rpc.conditions.callback.provider.service.CallbackService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author anLA7856
 * @date 19-7-18 下午10:11
 * @description
 */
public class CallbackServiceImpl implements CallbackService {

    private final Map<String, CallbackListener> listeners;

    public CallbackServiceImpl() {
        listeners = new ConcurrentHashMap<>();
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    for (Map.Entry<String, CallbackListener> entry : listeners.entrySet()) {
                        try {
                            entry.getValue().changed(getChanged(entry.getKey()));
                        } catch (Throwable t1) {
                            listeners.remove(entry.getKey());
                        }
                    }
                    Thread.sleep(5000); // timely trigger change event
                } catch (Throwable t1) {
                    t1.printStackTrace();
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void addListener(String key, CallbackListener listener) {
        listeners.put(key, listener);
        listener.changed(getChanged(key)); // send notification for change
    }

    private String getChanged(String key) {
        return "Changed: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
