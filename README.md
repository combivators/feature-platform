## Feature Platform: 一个基于Tiny Service微服框架构筑的具有AI功能的未来型人才信息平台
## 设计目的
 - 用于验证各项系统核心服务机能。

##Usage

###1. Simple Run
```java
java net.tiny.boot.Main --verbose
```


###2. Application configuration file with profile
```properties
Configuration file : application-{profile}.[yml, json, conf, properties]

main = ${launcher}
daemon = true
executor = ${pool}
callback = ${services}
pool.class = net.tiny.service.PausableThreadPoolExecutor
pool.size = 2
pool.max = 10
pool.timeout = 1
services.class = net.tiny.service.ServiceLocator
launcher.class = net.tiny.ws.Launcher
launcher.builder.bind = 192.168.1.1
launcher.builder.port = 80
launcher.builder.backlog = 10
launcher.builder.stopTimeout = 1
launcher.builder.executor = ${pool}
launcher.builder.handlers = ${rest}, ${health}
rest.class = net.tiny.ws.rs.RestfulHttpHandler
rest.path = /v1/api
rest.filters = ${logger}, ${params}
rest.factory.class = net.tiny.ws.rs.RestServiceFactory
rest.factory.application = ${rest.application}
rest.application.class = net.tiny.ws.rs.RestApplication
rest.application.pattern = "your.rest.*, !java.*, !com.sun.*"
health.class = net.tiny.ws.VoidHttpHandler
health.path = /health
health.filters = ${logger}
logger.class = net.tiny.ws.AccessLogger
logger.format = COMBINED
logger.file = /var/log/http-access.log
params.class = net.tiny.ws.ParameterFilter
```


##More Detail, See The Samples

---
Email   : wuweibg@gmail.com
