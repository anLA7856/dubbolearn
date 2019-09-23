## 条件路由 conditions 子包下面

enabled: true
force: true
runtime: true
conditions:
  - 'interface=HelloService&method=hello=>address=*:20880'
  - 'interface=HelloService&method=hello2=>address=*:20882'



## 文档
https://dubbo.apache.org/zh-cn/docs/user/demos/routing-rule.html