## dubbo monitor 需要在配置文件中使用 
```
    <dubbo:monitor protocol="registry" interval="100"/>
```
标签来指明注册中心，从而在请求时候，类似于向其发送数据。
如果想上面默认，则是registry，即注册中心存储

未来会放在 dubbo admin里面。