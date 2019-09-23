当一个接口有多种实现时，可以用 group 区分。
功能类似于 version 区分服务



## metadata-report
本例子还加入了一个metadata-report

作用是将dubbo一些元数据存储到zk中，当然，dubbo也支持将其存入redis中。

可看手册：
https://dubbo.apache.org/zh-cn/docs/user/references/metadata/introduction.html

例子：

```
{
	"parameters": {
		"side": "provider",
		"release": "2.7.2",
		"methods": "hello",
		"deprecated": "false",
		"dubbo": "2.0.2",
		"interface": "HelloService",
		"version": "1.0.0",
		"timeout": "5000",
		"generic": "false",
		"revision": "1.0.0",
		"application": "provider",
		"dynamic": "true",
		"register": "true",
		"bean.name": "HelloService",
		"group": "groupCat",
		"anyhost": "true"
	},
	"canonicalName": "HelloService",
	"codeSource": "file:/D:/IdeaProjects/dubbolearn/group/groupprovider/target/classes/",
	"methods": [{
		"name": "hello",
		"parameterTypes": ["java.lang.String"],
		"returnType": "java.lang.String"
	}],
	"types": [{
		"type": "int"
	}, {
		"type": "java.lang.String",
		"properties": {
			"value": {
				"type": "char[]"
			},
			"hash": {
				"type": "int"
			}
		}
	}, {
		"type": "char"
	}]
}
```