package com.anla.rpc.conditions.filter.provider.api;

import java.io.Serializable;

/**
 * @author anLA7856
 * @date 19-7-18 下午11:35
 * @description
 */
public class Dog implements Serializable {
   private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
