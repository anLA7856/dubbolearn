I found an optimization in when using Dubbo.
problem description:
My project tree(Consumer side):
```
│  Consumer.java
├─aaconfig
│      MyPriorityReferenceConfig.java
├─adubbo
│      HelloDubbo.java
└─dubbo
        MyReferenceConfig.java
```
I have two config file. `MyReferenceConfig.java` and `MyPriorityReferenceConfig.java` have the same code.
```
@Configuration
public class XXX {
    @Reference(version = "1.0.0", check = false)
    HelloService helloService;
}
```

`HelloDubbo.java` contains a Spring annotation field.
```
@Component
public class HelloDubbo {
    HelloService helloService;
    @Resource
    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }
}
```
When only `MyPriorityReferenceConfig.java` be register in Spring Context. It will throw an Exception:
```
org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.anla.rpc.configcenter.provider.service.HelloService'
```
However, When only `MyReferenceConfig.java` be register in Spring Context. It can found that bean.

I found `@Reference` will register a singleton bean , with type `ReferenceBean`, in Spring Context. 
with the help of that, I can use annotation `@Autowired` or `@Resource` to inject that. 
But it affected by the sequence of bean's initialization.
So it will throw `NoSuchBeanDefinitionException` when register `MyPriorityReferenceConfig.class` as bean into Spring Context only.


I write a class named `ReferenceBeanFactoryPostProcessor.class` to solve this problem.
It  go through all beans in SpringContext will store them.
when `postProcessMergedBeanDefinition` in `MergedBeanDefinitionPostProcessor` calls, it will find some beans to call `getBean`.