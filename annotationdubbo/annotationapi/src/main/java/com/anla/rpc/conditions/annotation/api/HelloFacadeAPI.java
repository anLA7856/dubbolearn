package com.anla.rpc.conditions.annotation.api;
import com.anla.rpc.conditions.annotation.dubbo.service.GoodByeService;
import com.anla.rpc.conditions.annotation.dubbo.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * 主要引用地方，最好可以是以接口形式暴露出去
 * @author anLA7856
 * @date 19-7-15 下午10:59
 * @description
 */
@Component("helloFacadeAPI")
public class HelloFacadeAPI {

    @Reference(interfaceClass = GoodByeService.class, version = "1.0.0")
    private GoodByeService goodByeService;
    @Reference(interfaceClass = HelloService.class, version = "1.0.0")
    private HelloService helloService;

    public String hello(String name) {
        return helloService.sayHello(name);
    }

    public String goodbye(String name) {
        return goodByeService.sayGoodBye(name);
    }
}
