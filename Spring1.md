# Spring 学习

#### Spring的设计初衷
##### 用于构造JAVA应用程序的轻量级框架
* 可以采用Spring来构造任何程序，而不局限于Web程序
* 轻量级：最少的侵入，于应用程序低耦合，接入成本低
* 最直观的感受：基于POJO，构建出稳健而强大的应用

#### Spring的野心
##### 为各大技术领域提供支持
* 微服务
* 社交API集成
* 运计算
* 移动开发
* 安全管理

# 环境准备
## 一、Spring模块梳理

### 1、Spring结构
####  -------------- 图片--------
#### Core Container ：容器是Spring的核心模块，Core Container是Spring框架的基础，所有模块都是构建在这个组件之上
##### (1)、Bean是Spring中的一等公民，Beans模块是所有应用都要用到的，它包含访问配置文件，创建和管理Bean，以及进行IOC和DI操作相关的类，其里边的BeanFactory接口，是Spring框架的核心接口，是我们应该学习的重点
##### (2)、Beans模块再牛逼，也得依赖Core模块，Core模块主要包含Spring框架的基本核心类，Spring的其他组件都要使用到这个包里的类，Core模块是其他组件的基本核心，当然也可以在自己的应用系统中使用这些工具类
##### (3)、Context模块，即Spring的上下文，也称为IOC容器，通过上下文可以获得容器中的Bean，该模块中的ApplicationContext是Context模块的关键，作为Beans模块里的BeanFactory的扩展，但是与BeanFactory默认的延时加载策略不同，ApplicationContext容器实例化之后，会自动对所有的单实例Bean进行实例化与依赖关系的装配，Context模块根据Core和Beans模块的基础之上，提供了一种类似于真DI注册器的框架式的对象访问方法
##### (4)、SpEL(Spring Expression Language)模块，提供了一个强大的表达式语言，用于在运行时查询和操纵对象，可用于将Bean和Bean属性注入到另外一个Bean，还支持Bean的方法调用，虽然作为Spring方法家族中表达式求值的基础，但却可以被独立出来使用

#### Spring AOP 方阵
Spring AOP 是Spring的另一个核心模块了，是AOP主要的模块了，AOP和IOC是Spring实现轻量级的超关键因素，需要重点了解，极大的开拓了人们对编程的思路

##### (1)、AOP模块，在Spring中它是以JDK的动态代理，以及CGLib动态代理技术为基础，设计出了一系列方法级别的AOP横切实现，比如前置通知，返回通知，异常通知等
##### (2)、Aspects模块 ：集成自AspectJ框架，主要是为Spring AOP 模块提供多种实现方法，在提供了AOP的便捷性之外，进一步扩展AOP的功能，像我们在开发中用到的Aspect标签这样的标签，其实均源自于AspectJ框架，而Spring AOP 中只使用该框架 Aspect 标签识别切面类的功能，其他更高级的功能，默认并未使用
##### (3)、Instrumentation模块 ：集成自AspectJ框架，是Spring AOP的一个资源模块，前面俩个模块主要是针对方法级别的切面编程，而Instruymentation模块主要支持对象级别的切面编程，作用是在JVM启动时生成一个代理类，通过代理类在运行时修改类的字节，从而改变一个类的功能，实现AOP的功能，多用于面向有状态的切面编程

#### Spring Messaging
##### (1)、Spring Messaging 模块，主要为集成Message API 和消息协议提供支持，可以解读为Spring的报文，通过该模块，Spring可以与市面主流的消息队列，如kafka等消息生成与消费的集成

#### Data Access/Integration 数据访问与集成
##### (1)、JDBC模块，Spring JDBC 提供对 JDBC 模块的封装功能，主要意义在于简化JDBC的使用，JDBC由一组由Java语言编写的类和接口组成，他们为多种关系型数据库提供统一的访问，而Spring JDBC 则提供了对JDBC操作的完整封装，包括在上边实现了一系列的模板
##### (2)、ORM模块，Spring ORM 作为 ORM 框架的支持模块，Spring支持市面主流的ORM框架，如Mybatis等，
##### (3)、OXM模块
##### (4)、JMS模块，Spring JMS 则提供消息队列的支持
##### (5)、Transaction 模块，Spring Transaction 模块则是 Spring JDBC 事务控制实现的模块，该模块则是对Spring AOP的最佳实现，它对事务做了很好的封装，通过它的AOP的配置，可以实现业务的完整流程，如果业务操作失败，则整个事务回滚

#### Web 组件，主要为Web开发提供服务
##### (1)、WebSoceket模块， 让Spring支持WebSocket，webSocket是我们HTML5下的一种全双工通信协议，在建立连接之后，服务端和客户端都可以向对方发送或者接收请求，就和Socket一样，WebSocket就可以看做HTTP为了满足长连接补得一个大补丁，Spring WebSocket消息体则依赖前面所讲的Spring Messaging模块
##### (2)、WebMVC，Spring WebMVC 则实现了 Model View Controller 的 web 应用，这是重点
##### (3)、Web，Spring Web 模块为 Spring 提供最基础的Web支持，主要建立于 SPring 容器之上，通过 Servlet 或者 Listeners 来初始化IOC容器，同时还简化了处理多部分请求以及将请求参数绑定到域对象的工作
##### (4)、WebFlux模块，是随着Spring5推出的响应式的Reactive Web模块，是基于响应式流的，可以用来建立异步非阻塞事件驱动的服务，扩展性也比较好

#### 上述模块如果要划重点：Bean、Core、Context、AOP

### 2、Spring基础核心模块预览
#### spring-core
* 包含框架基本的核心工具类，其他组件都要使用到这个包里的类
* 定义并提供资源的访问方式
* 为Spring的 IOC 和 DI 提供最基础的服务

#### spring-bean : Spring主要面向Bean编程(BOP)
* Bean的定义
* Bean的解析
* Bean的创建 (对于码农来说，唯一需要关系的就是Bean的创建，其他两个已经由Spring在内部完成了，对码农来说是透明的)
* BeanFactory 以及其实现子类，是我们的重点研究对象

#### spring-context
* 为Spring提供运行时环境，保存对象的状态
###### 我们知道Bean包装的是Object，而Object必然有数据，如何给数据提供生成环境，就是context要解决的问题，对于context来说，它就是要发现每个Bean之间的关系，为它们建立这种关系，并且维护好这种关系，所以context是Bean之间关系的集合
* 扩展了BeanFactory，为它添加了Bean的声明周期控制，框架事件体系，资源透明化等功能，此外该模块还提供了很多企业级的支持，如：事件监听，远程访问等
* ApplicationContext是该模块的核心接口，它是BeanFactory的子类，与BeanFactory默认延迟加载策略不同，ApplicationContext实例化之后，会自动对所有的单实例Bean进行实例化，以及相关依赖关系的装配，使之处于代用状态

#### spring-aop : 最小化的动态代理实现
###### AOP模块并不是Spring发明的，而Spring的初衷也并不是开发一个大而全的AOP框架，spring-aop只支持部分AOP的功能，作为一个轻量级框架，大道至简，实现了AOP 20% 的技术，满足了80%的需求，剩下的20%的开发中不常见的需求，可以使用spring-aspects和spring-instrumentation俩个模块来完成
* JDK动态代理
* Cglib 
* 只能使用运行时织入，仅支持方法级编织，仅支持方法执行切入点

#### spring-aspectj + spring-instrument : Full AspectJ
###### 俩者共同组成了完整的AspectJ模块
* 编译器Weaving
* 类加载器Weaving
* 运行期Weaving