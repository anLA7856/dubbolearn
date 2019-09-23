package com.anla.rpc.conditions.sentinel.provider;

import com.alibaba.csp.sentinel.init.InitExecutor;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.anla.rpc.conditions.sentinel.provider.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/30 17:50
 **/
public class Provider {


    private static final String INTERFACE_RES_KEY = HelloService.class.getName();

    public static void main(String[] args) throws InterruptedException {
        // Users don't need to manually call this method.
        // Only for eager initialization.
        InitExecutor.doInit();

        initFlowRule();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/provider.xml");
        context.start();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }

    private static void initFlowRule() {
        FlowRule flowRule = new FlowRule(INTERFACE_RES_KEY)
                .setCount(10)
                .setGrade(RuleConstant.FLOW_GRADE_QPS);
        FlowRuleManager.loadRules(Collections.singletonList(flowRule));
    }

}
