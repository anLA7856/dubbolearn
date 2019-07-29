## 多注册中心
Dubbo 支持同一服务向多注册中心同时注册，或者不同服务分别注册到不同的注册中心上去，甚至可以同时引用注册在不同注册中心上的同名服务。另外，注册中心是支持自定义扩展的。


类似的consumer.xml:
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
       xmlns="http://www.springframework.org/schema/beans" xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://dubbo.apache.org/schema/dubbo http://dubbo.apache.org/schema/dubbo/dubbo.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
    <context:property-placeholder/>

    <dubbo:application name="multi-registry-consumer"/>

    <dubbo:registry id="beijingRegistry" address="zookeeper://${zookeeper.address:127.0.0.1}:2181" default="false"/>
    <dubbo:registry id="shanghaiRegistry" address="zookeeper://${zookeeper.address:127.0.0.1}:2182"/>

    <dubbo:reference id="demoServiceFormDefault" interface="org.apache.dubbo.samples.multi.registry.api.DemoService"/>
    <dubbo:reference id="demoServiceFormBeijing" interface="org.apache.dubbo.samples.multi.registry.api.DemoService"
                     registry="beijingRegistry"/>
    <dubbo:reference id="helloServiceFormBeijing" interface="org.apache.dubbo.samples.multi.registry.api.HelloService"
                     registry="beijingRegistry"/>
    <dubbo:reference id="helloServiceFormShanghai" interface="org.apache.dubbo.samples.multi.registry.api.HelloService"
                     registry="shanghaiRegistry"/>

</beans>


```

provider.xml:
```
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
       xmlns="http://www.springframework.org/schema/beans" xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://dubbo.apache.org/schema/dubbo http://dubbo.apache.org/schema/dubbo/dubbo.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
    <context:property-placeholder/>

    <dubbo:application name="multi-registry-provider"/>

    <dubbo:registry id="beijingRegistry" address="zookeeper://${zookeeper.address:127.0.0.1}:2181" default="false"/>
    <dubbo:registry id="shanghaiRegistry" address="zookeeper://${zookeeper.address:127.0.0.1}:2182"/>

    <dubbo:protocol name="dubbo" port="20890"/>

    <bean id="helloService" class="org.apache.dubbo.samples.multi.registry.impl.HelloServiceImpl"/>
    <bean id="demoService" class="org.apache.dubbo.samples.multi.registry.impl.DemoServiceImpl"/>

    <dubbo:service interface="org.apache.dubbo.samples.multi.registry.api.HelloService" ref="helloService"
                   registry="shanghaiRegistry,beijingRegistry"/>
    <dubbo:service interface="org.apache.dubbo.samples.multi.registry.api.DemoService" ref="demoService"/>

</beans>

```