package com.anla.rpc.callback.consumer;

import com.anla.rpc.callback.provider.service.CallbackListener;
import com.anla.rpc.callback.provider.service.CallbackService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author anLA7856
 * @date 19-7-18 下午10:20
 * @description
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer.xml");
        context.start();

        CallbackService callbackService = context.getBean("callbackService", CallbackService.class);
        // 增加listener
        callbackService.addListener("foo.bar", new CallBackDemo());
    }


    static class CallBackDemo implements CallbackListener{

        @Override
        public void changed(String msg) {
            System.out.println("I am callback:" + msg);
        }
    }
}
