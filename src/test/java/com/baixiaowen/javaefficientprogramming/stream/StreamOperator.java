package com.baixiaowen.javaefficientprogramming.stream;

import com.alibaba.fastjson.JSON;
import com.baixiaowen.javaefficientprogramming.lambda.cart.CartService;
import com.baixiaowen.javaefficientprogramming.lambda.cart.Sku;
import com.baixiaowen.javaefficientprogramming.lambda.cart.SkuCategoryEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;

/**
 * 演示流的各种操作
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StreamOperator {

    List<Sku> list;

    @BeforeAll
    public void init() {
        list = CartService.getCartSkuList();
    }

    /**
     * filter 使用 ：过滤掉不符合断言判断的数据
     */
    @Test
    public void filterTest() {
        list.stream()
                // filter
                .filter(sku -> SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                .forEach(item -> System.err.println(JSON.toJSONString(item, true)));
    }

    /**
     * map 使用 ：将一个元素转换成另一个元素
     */
    @Test
    public void mapTest() {
        list.stream()
                // map
//                .map(Sku::getSkuName)
                .map(MapVs::apply)
                .forEach(item -> System.err.println(JSON.toJSONString(item, true)));
    }

    /**
     * flatMap 使用 ：将一个对象转换成流
     */
    @Test
    public void flatMapTest() {
        list.stream()
                // flatMap
                .flatMap(sku -> Arrays.stream(sku.getSkuName().split("")))
                .forEach(item -> System.err.println(JSON.toJSONString(item, true)));
    }

    /**
     * peek 使用 ：对流中元素进行遍历操作， 与forEach类似，但不会销毁流元素
     */
    @Test
    public void peekTest() {
        list.stream()
                // peek
//                .peek(sku -> System.out.println(sku.getSkuName()))
                .peek(Sku::peekPrint)
                .forEach(item -> System.err.println(JSON.toJSONString(item, true)));
    }

    /**
     * sorted 使用 ：对流中元素进行排序，可选择自然排序或指定排序规则，
     *      中间操作 -- 有状态的操作
     */
    @Test
    public void sortedTest() {
        list.stream()
                .peek(Sku::peekPrint)
                // sorted 有状态的sorted的中间操作
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .forEach(item -> System.err.println(JSON.toJSONString(item, true)));
    }

    /**
     * distinct 使用 ：对流中元素进行去重
     *      中间操作 -- 有状态操作
     */
    @Test
    public void distinctTest(){
        list.stream()
                .map(Sku::getSkuCategory)
                // distinct
                .distinct()
                .forEach(item -> System.err.println(JSON.toJSONString(item, true)));
    }

    /**
     * skip 使用 ：跳过前n条记录
     *      中间操作 -- 有状态操作
     */
    @Test
    public void skipTest(){
        list.stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .skip(3)
                .forEach(item -> System.err.println(JSON.toJSONString(item, true)));
    }

    /**
     * limit 使用 ：截断前n条记录
     *      中间操作 -- 有状态操作
     */
    @Test
    public void limitTest(){
        list.stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .skip(1 * 3)
                .limit(3)
                .forEach(item -> System.err.println(JSON.toJSONString(item, true)));
    }

    /**
     * allMatch 使用 ：用来检测所有元素是否都满足断言，如果都满足断言返回true，如果有一个不满足返回false，
     *      终端操作 -- 短路操作
     */
    @Test
    public void allMatchTest(){
        boolean match = list.stream()
                .peek(sku -> System.err.println(sku.getSkuName()))
                .allMatch(sku -> sku.getTotalPrice() > 100);
        System.err.println(match);
    }

    /**
     * anyMatch 使用 ：只要有元素满足断言，就返回true，否则返回false
     *      终端操作 -- 短路操作
     */
    @Test
    public void anyMatch(){
        boolean match = list.stream()
                .peek(sku -> System.err.println(sku.getSkuName()))
                .allMatch(sku -> sku.getTotalPrice() > 100);
        System.err.println(match);
    }

    /**
     * noneMatch 使用 ：所有元素都没匹配上返回true，否则返回false
     *      终端操作 -- 短路操作
     */
    @Test
    public void noneMatchTest(){
        boolean match = list.stream()
                .peek(sku -> System.err.println(sku.getSkuName()))
                .noneMatch(sku -> sku.getTotalPrice() > 10_000);
        System.err.println(match);
    }

    /**
     * findFirst 使用 ：找到第一个
     *      终端操作 -- 短路操作
     */
    @Test
    public void findFirstTest(){
        Optional<Sku> optional = list.stream()
                // findFirst
                .findFirst();
        System.err.println(JSON.toJSONString(optional.get(), true));
    }

    /**
     * findAny 使用 ：找到任意一个，只要有就返回
     *      终端操作 -- 短路操作
     *
     *      findAny 和 findFirst 在并行上有区别，
     *      findFirst 在并行上限制会更多一些，findAny 相对 findFirst 更快
     *      findAny 和 findFirst 在串行上是没有区别的
     */
    @Test
    public void findAnyTest(){
        Optional<Sku> optional = list.stream()
                // findAny
                .findAny();
        System.err.println(JSON.toJSONString(optional.get(), true));
    }

    /**
     * max 使用 ：获取最大值
     *      终端操作 -- 非短路操作
     */
    @Test
    public void maxTest(){
        OptionalDouble optionalDouble = list.stream()
                // mapToDouble : 将一个元素映射成一个Double类型
                // 获取总价
                .mapToDouble(Sku::getTotalPrice)
                // max
                .max();

        System.err.println(JSON.toJSONString(optionalDouble.getAsDouble(), true));
    }

    /**
     * min 使用 ：获取最小值
     *      终端操作 -- 非短路操作
     */
    @Test
    public void minTest(){
        OptionalDouble optionalDouble = list.stream()
                // mapToDouble : 将一个元素映射成一个Double类型
                // 获取总价
                .mapToDouble(Sku::getTotalPrice)
                // min
                .min();

        System.err.println(JSON.toJSONString(optionalDouble.getAsDouble(), true));
    }

    /**
     * count 使用 ：获取Stream流中所有元素的个数并且返回
     *      终端操作 -- 非短路操作
     */
    @Test
    public void countTest(){
        long count = list.stream()
                // count
                .count();

        System.err.println(JSON.toJSONString(count, true));
    }
}