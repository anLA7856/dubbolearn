## 介绍

mock的例子，
就是最开始看源码中的 `MockClusterInvoker`
了解完mock，也就知道为什么 `MockClusterInvoker` 会在第一个过滤器invoker中

如果在reference上面配置了mock为true，则当调用不成功时候，会调用mock服务

也就是当provider不启动时候，会调用mock哦


具体可以参看文档：
http://dubbo.apache.org/zh-cn/docs/user/demos/local-mock.html

分析：
https://www.jianshu.com/p/ce8de35986cf
可能有点过时