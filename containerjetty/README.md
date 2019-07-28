## 介绍
dubbo的container
Dubbo的Container详解模块，是一个独立的容器，因为服务通常不需要Tomcat/JBoss等Web容器的特性，没必要用Web容器去加载服务。
服务容器只是一个简单的Main方法，并加载一个简单的Spring容器，用于暴露服务。

## 讲解
以前的例子都是直接一个简单main方法
本届例子是一个Container例子

dubbo里面有几个container：
1. SpringContainer。
2. Log4jContainer。
3. JettyContainer。
4. JavaConfigContainer。
5. LogbackContainer。

具体：
http://www.ccblog.cn/75.htm

## 本例子没有走完
可以作为 container 的spi例子