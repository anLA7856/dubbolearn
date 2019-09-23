package com.anla.rpc.conditions.groupconsumer;

import com.anla.rpc.conditions.groupprovider.service.HelloService;
import org.apache.dubbo.common.constants.CommonConstants;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author anLA7856
 * @date 19-7-28 下午3:57
 * @description
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer.xml");
        context.start();

        HelloService helloCatService = context.getBean("helloCatService", HelloService.class);
        HelloService helloDogService = context.getBean("helloDogService", HelloService.class);

        String resultGroupCat = helloCatService.hello("miamo");
        System.out.println(resultGroupCat);

        String resultGroupDog = helloDogService.hello("miamo");
        System.out.println(resultGroupDog);

        // 获取zk的配置信息
        printServiceData();
    }

    private static void printServiceData() throws Exception {
        Thread.sleep(3000);
        System.out.println("*********************************************************");
        System.out.println("service metadata:");
        System.out.println(ZKTools.getMetadata("/dubboMetadata", HelloService.class.getName(), "1.0.0", "groupCat",
                CommonConstants.CONSUMER_SIDE, "consumer"));
        System.out.println("*********************************************************");
    }
}
