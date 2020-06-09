## Stream流操作

### 1、实战案例: 集合与流操作对比
* 实战：分别使用集合操作及Stream流操作，完成对实际引用场景中的数据处理。直观感受流操作带来的便捷性。
* 具体代码可查看test中的stream包相关类


### 2、流是什么？
* JDK1.8引入的新成员，以声明式方式处理集合数据
* 将基础操作链接起来，完成复杂的数据处理流水线
* 提供透明并行处理

#### 从支持 数据处理操作 的 源 生成的 元素序列 (Java8实战)

### 3、流与集合的区别
* 时间与空间
###### 集合就相当于以前看的DVD光碟，只有把光碟放到DVD机里，才能观看整部电影
###### 流相当于现在的流媒体播放，不用获取到整部电影的所有的数据帧，主需要获取到当前正在观看的一定时间范围内的数据帧就可以了
###### 从这个角度上看，集合更像是一个空间上的集合存储，而流更像一个时间维度的元素的生成

|集合|流|
|---|---|
|集合更像是一个空间上的集合存储|更像是一个时间维度的元素的生成|
|集合面向是存储|流面向的是计算|

* 只能遍历一次
###### 集合是一个空间上的概念，所以它能遍历多次，流是一个时间上的概念，所以它只能遍历一次，再次遍历就会出现异常

* 集合需要做外部迭代，流做内部迭代

### 4、流的组成
|Cart|filter|sorted|map|collect|
|:---:|:---:|:---:|:---:|:---:|
|数据源|中间操作|中间操作|中间操作|终止操作|
###### 中间操作是对应的一些业务操作，
###### 终端操作是对流数据的一个收集，可以将数据源产生的数据进行一个收集产生一个数据结构


### 5、流操作的分类
* 流操作分类
    > 中间操作(Intermediate)
    
    > > 无状态操作       -- filter/map/peek等
    
    > > 有状态操作     -- distinct/sorted/limit等
    
    > 终端操作(Terminal)
    
    > > 非短路操作       -- forEach/collect/count等
    
    > > 短路操作         -- anyMatch/findFirst/findAny等
    
### 6、流的使用
|中间操作(无状态)|中间操作(有状态)|终端操作(短路)|终端操作(非短路操作)|
|:---:|:---:|:---:|:---:|
|过滤(filter)|去重(distinct)|所有匹配(allMatch)|遍历(forEach)|
|映射(map)|跳过(skip)|任意匹配(anyMatch)|归约(reduce)|
|扁平化(flatMap)|截断(limit)|不匹配(noneMatch)|最大值(max)|
|遍历(peek)|排序(sorted)|查找首个(findFirst)|聚合(collect)|
| | |查找任意(findAny)|最小值(min)|
| | | |计数(count)|    


### 7、流的构建
* 由值创建流
* 由数组创建流
* 由文件生成流
* 由函数生成流(无限流)


### 8、收集器
###### 流的收集
#### (1)简介
* 将流中的元素累积成一个结果(数据、集合、分组)
* 作用于终端操作collect()上
* collect / Collector / Collectors

|collect|Collector|Collectors|
|:---:|:---:|:---:|
|作为一个终端操作出现的，是流出现的最后一个步骤|是一个接口，collect()方法需要接收一个实现了Collector接口的收集器|工具类，帮我们提前封装了一些已经定制好的实现了Collector接口的收集器|

#### (2)、预定义收集器功能
* 将流元素归约和汇总为一个值
* 将流元素分组
* 将流元素分区


### 9、归约与汇总
* 归约(reduce) : 将Stream流中元素转换成一个     值
* 汇总(collect) : 将Stream流中元素转换成一个    容器

#### (1)、归约
##### 将Stream流中元素转换成一个值！！！
```java
        Stream<Integer> integerStream = Lists.newArrayList(1,2,3).stream();
        
        // 求最大值
        integerStream.mapToInt(Integer::intValue).max();
        // 求最小值
        
        integerStream.mapToInt(Integer::intValue).min();
        
        // 求和
        integerStream.mapToInt(Integer::intValue).sum();
        
```

##### reduce接口参数
```java
        /**
        * 归约操作接口定义
        * @param identity - 初始值
        * @param accumulator - 计算逻辑
        * @param combiner - 并行执行时多个部分结果的合并方式
        * @param <U> - 元素类型
        * @return 
        */
        <U> U reduce(U identity,
                         BiFunction<U, ? super T, U> accumulator,
                         BinaryOperator<U> combiner);
```

#### (2)、汇总
##### 将Stream流中元素转换成一个容器！！！
```java
    import java.util.List;import java.util.Map;import java.util.stream.Collectors;import java.util.stream.Stream;class A {
        void method(){
            Stream<Integer> integerStream = Lists.newArrayList(1, 2, 3, 4).stream();
            
            // 转换成List集合
            List<Integer> list = integerStream.collect(Collectors.toList());
            // 按奇偶区分
            Map<Boolean, List<Integer>> partitions = integerStream.collect(
                Collectors.partitioningBy(item -> item % 2 == 0));
            // 按元素分组
            Map<Integer, List<Integer>> groups = integerStream.collect(Collectors.groupingBy(item -> item));
        }
    }   
```
##### collect 接口参数

```java
    class A{
        /**
        * 汇总操作接口定义
        * @param supplier - 初始化结果容器
        * @param accumulator - 添加元素到结果容器逻辑
        * @param combiner - 并行执行时多个结果容器的合并方式
        * @param <R> - 元素类型
        * @return 
        */
        <R> R collect(Supplier<R> supplier,
                          BiConsumer<R, ? super T> accumulator,
                          BiConsumer<R, R> combiner);
    }   
```

#### (2)、汇总
##### 将Stream流中元素转换成一个容器！！！
```java
    import java.util.List;import java.util.Map;import java.util.stream.Collectors;import java.util.stream.Stream;class A {
        void method(){
            Stream<Integer> integerStream = Lists.newArrayList(1, 2, 3, 4).stream();
            
            // 转换成List集合
            List<Integer> list = integerStream.collect(Collectors.toList());
            // 按奇偶区分
            Map<Boolean, List<Integer>> partitions = integerStream.collect(
                Collectors.partitioningBy(item -> item % 2 == 0));
            // 按元素分组
            Map<Integer, List<Integer>> groups = integerStream.collect(Collectors.groupingBy(item -> item));
        }
    }   
```
##### collect 接口参数

```java
    class A{
        /**
        * 汇总操作接口定义
        * @param supplier - 初始化结果容器
        * @param accumulator - 添加元素到结果容器逻辑
        * @param combiner - 并行执行时多个结果容器的合并方式
        * @param <R> - 元素类型
        * @return 
        */
        <R> R collect(Supplier<R> supplier,
                          BiConsumer<R, ? super T> accumulator,
                          BiConsumer<R, R> combiner);
    }   
```

#### 总结：自定义的汇总和归约方法，在定义并行结果合并的时候，需要处理的点有很多，因为并行的时候就会涉及到线程，涉及到冲突，涉及到原子性等等，所以非常不建议自己去做这种自定义的逻辑实现
###### JDK为我们封装了一些非常常用的归约和汇总操作，覆盖了我们使用到的大量的场景，所以推荐使用这些

```java
    class A {
        /**
        * 汇总操作接口定义
        * @param collector - Collector接口实现类 收集器接口
        * @param <R> 
        * @param <A>
        * @return 
        */
        <R, A> R collect(Collector<? super T, A, R> collector);
    }
```

```java
    /**
    * Collector 接口
    * @param <T> 流中要收集的项目的泛型
    * @param <A> 累加器的类型，累加器是在收集过程中用于累积部分结果的对象
    * @param <R> 收集操作得到的对象的类型
    * 例如 ：public class ToList<T> implements Collector<T, List<T>, List<T>>
    */
    public interface Collector<T, A, R> {
        // 建立新的结果容器
        Supplier<A> supplier();
        // 将元素添加到结果容器
        BiConsumer<A, T> accumulator();
        // 合并连个结果容器
        BinaryOperator<A> combiner();
        // 对结果容器应用最终转换 
        Function<A, R> finisher();
        // 定义收集器行为，如 ：是否可以并行，可以使用那些优化
        Set<Characteristics> characteristics();
    }
```

### 9、实际案例

#### (1)、【查找】- 班级中有20名学生，每名学生有5门课的考试成绩，其中缺考的科目分数字段为空。需要找出有缺考的学生都叫什么名字。
#### (2)、【去重】- 标签管理功能模块。允许用户批量添加标签，后台需要对标签去重，并且需要防止数据库中存在同名的标签
#### (3)、【扁平化】- 权限管理功能模块。查询某用户所有角色下所包含的权限名称
#### (4)、【分组】- 设计一个对外提供服务的接口，支持调用方法传入多个账户编号查询订单信息
#### (5)、【排序】- 在股票中，撮合交易的原则是一段时间内的交易申请，价格越高的先成交；价格一样，下单时间最早的先成交；价格和时间一致，交易量大的先成交；如果价格、时间和交易量都一致，机构优先成交，散会最后成交。现有一批交易申请数据，需要确认交易先后顺序 
