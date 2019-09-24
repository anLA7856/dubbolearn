package com.anla.rpc.filter.provider.service;

import com.anla.rpc.filter.provider.api.Dog;

/**
 * @author anLA7856
 * @date 19-7-18 下午11:37
 * @description
 */
public interface HelloService {
    /**
     * 用于hello
     * @param name
     * @return
     */
    String hello(String name);

    /**
     * 获取狗子
     * @return
     */
    Dog getDog(int id);
}
