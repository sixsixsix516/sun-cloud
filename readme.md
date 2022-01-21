### sun-cloud
微服务开发脚手架，包含微服务环境下大部分遇到的问题解决方案，代码简洁，层次清晰
使用常见的组件构成

技术栈
1. 服务注册发现 / 配置中心 Nacos
2. 服务调用 Feign
3. 网关 gateway
4. 安全 OAuth2
5. 链路监控 SkyWalking
6. 限流 Sentinel
7. 监控 prometheus
8. 日志收集 ELK
9. 分布式事务 Seata
10. 分布式文件
11. DevOps travis Docker
12.


链路监控的使用方式
添加JVM参数 -javaagent:D:\app\component\apache-skywalking-apm-bin-es7\agent\skywalking-agent.jar -Dskywalking.agent.service_name=sun-gateway -Dskywalking.collector.backend_service=127.0.0.1:11800



待实现
1. 一个工具：填入包名，作者名，直接全局替换相关地方！

优化项
1. 版本号的定义, 不可以写死
2. 令牌：未登录，过期等的返回值需要和系统返回值全局统一
3. 将后台管理快速开发代码挪进来
4. 多账号下，安全服务如何设计？
5. 