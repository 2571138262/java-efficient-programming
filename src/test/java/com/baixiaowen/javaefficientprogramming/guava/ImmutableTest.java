package com.baixiaowen.javaefficientprogramming.guava;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 不可变集合用法
 */
public class ImmutableTest {

    public static void test(List<Integer> list){
        list.remove(0);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        // 使用传统的JDK 的Collections工具类中的方法来讲集合变成一个不可变集合，但是相对笨重
        List<Integer> newList = Collections.unmodifiableList(list);

        test(newList);

        System.err.println(newList);
    }

    /**
     * 使用 Guava 的 ImmutableSet类的方法来实现不可变集合
     */
    public void immutable(){
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        /**
         * 构造不可变集合对象的三种方式
         */
        // 1、通过已经存在的集合创建
        ImmutableSet.copyOf(list);

        // 2、通过初始值，直接创建不可变集合
        ImmutableSet<Integer> immutableSet = ImmutableSet.of(1, 2, 3);

        // 3、以builder方式创建
        ImmutableSet.builder()
                .add(1)
                .addAll(Sets.newHashSet(1, 2))
                .add(4)
                .build();


    }

}