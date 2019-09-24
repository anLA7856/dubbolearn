package com.anla.rpc.apiconfigcenter.provider;

import com.anla.rpc.apiconfigcenter.provider.service.impl.HelloServiceImpl;
import com.anla.rpc.apiconfigcenter.provider.service.HelloService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConfigCenterConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author anLA7856
 * @date 19-7-22 下午11:36
 * @description
 */
public class Provider {
    private static ConfigCenterConfig configCenter = new ConfigCenterConfig();
    private static ApplicationConfig applicationConfig = new ApplicationConfig("api-dubbo-provider-1");
    static {
        // 设置配置中心信息
        configCenter.setExternalConfig(getExternalConfiguration());
    }
    public static void main(String[] args) throws Exception {
        ServiceConfig<HelloService> service = new ServiceConfig<>();
        service.setApplication(applicationConfig);
        service.setConfigCenter(configCenter);
        service.setInterface(HelloService.class);
        service.setRef(new HelloServiceImpl());
        service.export();
        System.out.println("dubbo service started");
        System.in.read();
    }

    public static Map<String, String> getExternalConfiguration() {
        Map<String, String> dubboConfigurations = new HashMap<>();
        dubboConfigurations.put("dubbo.registry.address", "zookeeper://127.0.0.1:2181");
        // you will need to add the configcenter address if you want to use the service governance features in 2.7,
        // e.g., overrides and routers, but notice it will not be used for gathering startup configurations.
        dubboConfigurations.put("dubbo.config-center.address", "zookeeper://127.0.0.1:2181");

        return dubboConfigurations;
    }
}
