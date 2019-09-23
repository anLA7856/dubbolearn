package com.anla.rpc.conditions.annotation.dubbo;

import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @PropertySource 需要放入子文件夹才能被识别，这就尴尬了。
 *
 * @author anLA7856
 * @date 19-7-15 下午10:55
 * @description
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.anla.rpc.annotation.dubbo.service.impl")
@PropertySource("classpath:/dubbo/dubbo-provider.properties")
public class ProviderConfiguration {
    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(1000);
        return providerConfig;
    }
}
