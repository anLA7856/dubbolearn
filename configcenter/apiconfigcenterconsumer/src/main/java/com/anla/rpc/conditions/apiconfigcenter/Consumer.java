package com.anla.rpc.conditions.apiconfigcenter;

import com.anla.rpc.conditions.apiconfigcenter.provider.service.HelloService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConfigCenterConfig;
import org.apache.dubbo.config.ReferenceConfig;

/**
 * @author anLA7856
 * @date 19-7-22 下午11:36
 * @description
 */
public class Consumer {
    private static ConfigCenterConfig configCenter = new ConfigCenterConfig();
    private static ApplicationConfig applicationConfig = new ApplicationConfig("api-dubbo-consumer");

    static {
        configCenter.setAddress("zookeeper://127.0.0.1:2181");
    }

    public static void main(String[] args) throws Exception {
        ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
        reference.setApplication(applicationConfig);
        reference.setConfigCenter(configCenter);
        reference.setInterface(HelloService.class);
        HelloService greetingsService = reference.get();
        String message = greetingsService.hello("dubbo");
        System.out.println(message);
    }
}
