package com.anla.springcloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * bug: https://github.com/alibaba/spring-cloud-alibaba/issues/1532
 *
 * @author luoan
 * @version 1.0
 * @date 2020/7/14 15:17
 **/
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(
            DemoApplication.class, args
        );
    }
}
