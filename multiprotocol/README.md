## 简介

使用了两种协议，分别是dubbo 和 和 hessan
这个需要分别在dubbo配置文件中声明，并且制定好端口，当然对应服务所有协议必须一致

hessian的有点：http://dubbo.apache.org/zh-cn/docs/user/references/protocol/hessian.html

 - 连接个数：多连接
 - 连接方式：短连接
 - 传输协议：HTTP
 - 传输方式：同步传输
 - 序列化：Hessian二进制序列化
 - 适用范围：传入传出参数数据包较大，提供者比消费者个数多，提供者压力较大，可传文件。
 - 适用场景：页面传输，文件传输，或与原生hessian服务互操作
 
## 可错误时候
1. 注意consumer和provider包下面，都需要引入hessian的包，否则会报错

## 注册中心
其实这个本来是注册中心例子，如果使用了注册中心，即加上
```
<dubbo:config-center address="zookeeper://127.0.0.1:2181"/>
``` 
这样可以省略一些公共配置，只要指定注册中心，其他的dubbo配置都可以省略。
即就是一个配置中心。