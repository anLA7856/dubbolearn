出现原因：
1. `@Reference` 所在的configuration，后初始化，，导致 此时 `@Reference` 还没有被加载，而 `@Refernce` 又还不是一个beanDefinition，且又不在`manualSingletonNames`中。
所以在检索时候，无法检索到。

想到的解决方法：
1. 将 带有 `@Reference` bean 优先调用getBean。有问题。
2. 在 扫描时候，将 `@Reference` 注册为BeanDefinition。类似于 `@Bean` 的方法注解。
3. 可以考虑建立集合缓存，如果里面有对应的`@Autowired` 的 `@Reference`类型，则有限调用 `@Reference` 类的getBean。



1. postProcessProperties 会比 @CommonAnnotation 和 @Autowired 先调用。