package com.anla.rpc.conditions.asyncprovider.service;

import java.util.concurrent.CompletableFuture;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/17 17:52
 **/
public interface HelloService {
    /**
     * hello
     * @param name
     * @return
     */
    String hello(String name);

    /**
     * 使用 CompletableFuture包装返回值
     * @param name
     * @param signal
     * @return
     */
    default CompletableFuture<String> greeting(String name, byte signal) {
        return CompletableFuture.completedFuture(hello(name));
    }
}
