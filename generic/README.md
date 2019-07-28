## 介绍
泛化调用主要用于消费端没有 API 接口的情况；不需要引入接口 jar 包，而是直接通过 GenericService 接口来发起服务调用，参数及返回值中的所有 POJO 均用 Map 表示。泛化调用对于服务端无需关注，按正常服务进行暴露即可。

##以下几种场景可以考虑使用泛化调用：

 - 服务测试平台
 - API 服务网关
 
 
## 注意，在 reference 需要加上 generic=true

具体参考：
http://dubbo.apache.org/zh-cn/blog/dubbo-generic-invoke.html