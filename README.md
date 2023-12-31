# common_project_base_springcloudalibaba
基于SpringCloudAlibaba的通用项目模板

# 业务框架总体思想
---
1. 新框架采用了 DDD 思想对整个系统进行了分层，将整个业务系统分为展示层、业务层以及基础设施层；
2. 通过将业务系统分层，解耦了原有系统中的耦合关系；
	1. 解耦 `数据` 与 `业务`，将数据的处理和数据的操作分开，通过多级缓存的引入提升数据的操作效率；解耦业务与数据源也可以提高系统对不同数据库的兼容能力；
	2. 解耦 `业务` 与 `协议`，将 `协议转换` 和 `数据处理` 解耦，通过扩展支持多种协议在系统中的应用，增强系统的适应能力；

3. 引入 SkyWalking + ES ，收集日志以及请求链路追踪，实现可视化的日志分析，赋能运维，提升运维排查效率；
4. Sentinel+nacos 组件，实现入口接口的流量控制，提高系统的健壮性；

# 业务模块介绍
## 架构图
https://www.processon.com/v/64e1e8d45adbb0271b239a1d
## 子模块介绍
- nginx：系统入口，主要负责反向代理，提供 websocket 服务；nginx 服务，放在 DMZ 区，隔离保护业务系统；
- gateway：流量入口网关，主要负责请求路由，接口鉴权；
- Auth：鉴权中心，主要负责用户的多端登录，登出；
- User：账户中心，主要负责用户信息以及用户归属组织架构管理；
- Display：展示中心，主要负责对接 WEB/APP 等来自用户端需求数据的整理聚合；
- Service：业务中心，主要负责业务的处理；⭐
- Sys：系统中心，主要负责系统的支持工作，负责第三方能力的对接；
- Monitor：监控中心，主要负责系统告警的收集以及告警展示与定制化处理；
- Algorithm：算法代理中心，主要负责各种算法的代理；
- Device：设备代理中心，主要负责各类硬件协议的代理；
- Data：数据中心，主要负责数据的增删改查，以及效率优化；
## 第三方组件
- nacos：配置中心，注册中心；
- skywalking：日志收集，链路追踪；
- es：日志和链路数据持久化；
- sentinel：流量控制，熔断降级；
