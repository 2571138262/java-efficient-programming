## 一、Lambda

### 1、Lambda表达式简介
* Java8印日函数式编程风格
###### 函数式编程是一种不同的编程思想，它定义函数作为一等公民，可以赋值给变量，作为参数或者返回值进行传递
###### java是面向对象的，万物皆对象，java8突破了只有类作为一等公民的设计，支持将函数作为参数进行传递，在编程方式上提供了很大的便捷
###### 在java之后，JVM平台出现了一些其他的编程语言，比如Scala、kotlin，这些多范式的编程语言比较受欢迎，Java完全面向对象就优点受限制了
* 可以理解为一种匿名函数的代替
* 通过行为参数化传递代码(将方法作为入参进行传递)

### 2、Lambda表达式的形式
* (parameters) -> expression
* (parameters) -> { statement; }

### 3、Lambda表达式的特性
* 可选的类型声明(可以不需要声明参数类型，编译器可以进行统一的识别参数)
* 可选的参数 () ，一个参数无须定义 () ，但是多个参数需要定义 () 
* 可选的 {}, 如果主体包含了一个表达式，就不需要使用 {} 
* 可选的返回关键字， 如果主体只要一个表达式返回值，则编译器会自动返回值， {} 大括号需要指明主体需要指明语句返回的数值
* 形式一 ：没有参数
###### () -> System.out.println("Hello World")
* 形式二 ：只有一个参数
###### name -> System.out.println("Hello World from " + name + "!");
* 形式三 ：没有参数，逻辑复杂
###### () -> { System.out.println("Hello"); System.out.println("World"); }
* 形式四 ：包含两个参数的方法
###### BinaryOperator<Long> functionAdd = (x, y) -> x + y;
###### Long result = functionAdd.apply(1L, 2L);
* 形式五 ：对参数显示声明
###### BinaryOperator<Long> functionAdd = (Long x, Long y) -> x + y;
###### Long result = functionAdd.apply(1L, 2L);

### 4、函数式接口
* 接口中只有一个抽象方法
* Java8的函数式接口注解 ：@FunctionInterface
* 函数式接口的抽象方法签名 ：函数描述符

### 5、常见函数接口及使用
| 接口 | 参数 | 返回类型 | 描述 | 
| --- | --- | --- | --- |
|Predicate< T >|T|boolean|用于判别一个对象。比如求一个人是否为男性|
|Consumer < T >|T|void|用于接收一个对象进行处理但没有返回，比如接收一个人并打印他的名字|
|Function < T,R >|T|R|转换一个对象为不同类型的对象|
|Supplier< T >|None|T|提供一个对象|
|UnaryOperator< T >|T|T|接收对象并返回同类型的对象|
|BinaryOperator< T >|T, T|T|接收两个同类型的对象，并返回一个原类型对象|


### 6、方法引用
###### 调用特定方法的Lambda表达式的一种快捷写法，可以让你重复使用现有的方法定义，并像Lambda表达式一样传递他们
| Sku | ：：| getSkuPrice |
| --- | --- | --- |
| 目标引用 | 双冒号分隔符 | 方法名|

#### (1)、指向静态方法的方法引用
```java
    /**
     * (args) -> ClassName.staticMethod(args);
     * ClassName::staticMethod;
     */
    public void test1(){
        Consumer<String> consumer1 = (String number) -> Integer.parseInt(number);
        Consumer<String> consumer2 = Integer::parseInt;
    }
```

#### (2)、指向任意类型实例方法的方法引用
```java
    /**
     * (args) -> args.instanceMethod();
     * ClassName::instanceMethod;
     */
    public void test2(){
        Consumer<String> consumer1 = (String str) -> str.length();
        Consumer<String> consumer2 = String::length;
    }
```

#### (3)、指向现有对象的实例方法的方法引用
```java
    /**
     * (args) -> object.instanceMethod(args);
     * Object::instanceMethod;
     */
    public void test3(){
        StringBuilder stringBuilder = new StringBuilder();
        
        Consumer<String> consumer1 = (String str) -> stringBuilder.append(str);
        Consumer<String> consumer2 = stringBuilder::append;
    }
```

### 7、方法引用精讲
#### (1)、Lambda表达式的由来
##### ①、使用实体类代表判断逻辑
```java
    // 过滤商品总价大于2000的商品
    List<Sku> result
            = CartService.filterSkus(cartSkuList, new SkuTotalPricePredicate());
```

##### ②、使用匿名类代表判断逻辑
```java
    // 过滤商品单价小于1000的商品
    List<Sku> result
            = CartService.filterSkus(cartSkuList, new SkuPredicate() {
        @Override
        public boolean test(Sku sku) {
            return sku.getSkuPrice() < 1000;
        }
    });
```

##### ③、使用Lambda表达式代表判断逻辑
```java
    // 过滤商品单价小于1000的商品
    List<Sku> result
            = CartService.filterSkus(cartSkuList, sku -> sku.getSkuPrice() > 1000);
```

#### (1)、方法引用的由来
##### ④、使用方法引用代表判断逻辑
```java
    // 假如Sku中包含价格比较的方法
    public class Sku{
        // 价格比较方法
        public Boolean comparePrice() {
            return this.skyuPrice > 1000;
        }
    }
    
    // 改写上边判断逻辑
    List<Sku> result
                = CartService.filterSkus(
                        cartSkuList, 
                        Sku::comparePrice);
```

#### (2)、方法引用的作用
###### 方法引用是用来直接访问类或者实例的已经存在的方法或者构造方法

#### (3)、方法引用的表达形式
| Sku | :: | getSkuPrice |
| --- | --- | --- |
| 目标引用 | 双冒号分隔符 | 方法名 |

#### (4)、方法引用分类
##### 四种方法引用类型
| 方法引用类型 | 表示形式 |
| --- | --- |
| 指向静态方法 | Class::staticMethod |
| 指向现有对象的实例方法 | object::instanceMethod |
| 指向任意类型的实例方法 | Class::instanceMethod |
| 指向构造方法 | Class::new |

