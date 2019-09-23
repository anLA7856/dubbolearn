## nofity例子
就是可以在方法调用时候，指定一些额外的操作，例如consumer.xml
```
    <dubbo:reference id="helloService" check="false" interface="HelloService"
                     version="1.0.0" >
        <dubbo:method name="hello" async="false" onreturn="notifyServiceImpl.onReturn" onthrow="notifyServiceImpl.onThrow"/>
    </dubbo:reference>
```