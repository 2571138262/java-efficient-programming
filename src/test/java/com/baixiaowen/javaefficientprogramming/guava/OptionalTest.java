package com.baixiaowen.javaefficientprogramming.guava;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 学习java8 中的Optional使用方法
 */
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OptionalTest {

    @Test
    public void test(){

        /**
         * 三种创建Optional对象方式
         */

        // 1、创建空的Optional对象
        Optional.empty();

        // 2、使用非null值创建Optional对象
        Optional.of("Baixiaowen");

        // 3、使用任意值创建Optional对象
        Optional<Object> optional = Optional.ofNullable("baixiaowen 测试 Optional 的 ifPresent方法");

        /**
         * 判断是否引用缺失的方法（建议不使用）
         */
        optional.isPresent();

        /**
         * 当Optional中的引用存在时，会执行这个方法
         * 当Optional里边的引用非空时，它会执行ifPresent方法
         * 类似的方法: map filter flatMap
         */
        optional.ifPresent(System.err::println);

        /**
         * 当Optional引用缺失时执行
         */
        optional.orElse("引用缺失");
        optional.orElseGet(() -> {
            // 当Optional引用缺失时 可以返回自定义引用缺失时的返回值
            return "自定义引用缺失";
        });
//        optional.orElseThrow(() -> {
//            // 当Optional引用缺失时，可以抛出一个异常
//            throw new RuntimeException("引用缺失异常");
//        });

    }

    public static void stream(List<String> list){
        // 这里直接调用空的集合会爆空指针异常
//        list.stream().forEach(System.err::println);

        // 通过Optional将其包裹，避免空指针
        Optional.ofNullable(list)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .forEach(System.err::println);
    }

    public static void main(String[] args) {
        stream(null);
    }

}
