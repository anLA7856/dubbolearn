package com.anla.rpc.configcenter.provider;

import com.anla.rpc.configcenter.provider.config.ProviderConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @author anLA7856
 * @date 19-7-22 下午10:45
 * @description
 */
public class Provider {
    public static void main(String[] args) throws Exception {
        // 生成zk配置，也可以通过自己去zk上面配置
        ZKConfigCenterTools.generateDubboProperties();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        context.start();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
