package com.anla.rpc.notify.consumer;

import com.anla.rpc.notify.consumer.api.impl.NotifyServiceImpl;
import com.anla.rpc.notify.provider.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author anLA7856
 * @date 19-7-29 下午11:15
 * @description
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer.xml");
        context.start();

        HelloService helloService = context.getBean("helloService", HelloService.class);
        NotifyServiceImpl notifyService = context.getBean("notifyServiceImpl", NotifyServiceImpl.class);

        int id = 1;
        String result = helloService.hello(id);
        System.out.println("result is" + result);
        for (int i = 0; i < 10; i++) {
            if (!notifyService.ret.containsKey(id)) {
                Thread.sleep(200);
            } else {
                break;
            }
        }

        System.out.println("result: " + notifyService.ret.get(id));
    }
}
