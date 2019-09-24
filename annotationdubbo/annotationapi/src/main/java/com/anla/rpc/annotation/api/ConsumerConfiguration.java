package com.anla.rpc.annotation.api;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author anLA7856
 * @date 19-7-15 下午10:55
 * @description
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.anla.rpc.annotation.api")
@PropertySource("classpath:dubbo/dubbo-consumer.properties")
@ComponentScan(value = {"com.anla.rpc.annotation.api"})
public class ConsumerConfiguration {

}
