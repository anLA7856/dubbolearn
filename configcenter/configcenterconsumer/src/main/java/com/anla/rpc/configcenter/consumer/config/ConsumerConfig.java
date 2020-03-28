package com.anla.rpc.configcenter.consumer.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author anLA7856
 * @date 19-7-22 下午10:36
 * @description
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.anla.rpc.configcenter.consumer", multipleConfig = true)   // 扫描 dubbo 包，主要是refernence注解等，开启多注册中心
@PropertySource("classpath:/dubbo/consumer.properties")
@ComponentScan(value = {"com.anla.rpc.configcenter.consumer"})
public class ConsumerConfig {

}
