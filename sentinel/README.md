## 简介
sentinel例子


在 provider中有配置：
```
        FlowRule flowRule = new FlowRule(INTERFACE_RES_KEY)
                .setCount(10)
                .setGrade(RuleConstant.FLOW_GRADE_QPS);
        FlowRuleManager.loadRules(Collections.singletonList(flowRule));
```

在consumer中：
所以当执行完10次后，即5次循环，就会报错


和 resilience4j相比，sentinel更加切合dubbo，不需要显式实现过滤器之类的