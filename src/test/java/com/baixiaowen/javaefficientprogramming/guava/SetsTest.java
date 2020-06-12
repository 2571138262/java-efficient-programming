package com.baixiaowen.javaefficientprogramming.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

/**
 * Lists / Sets 使用
 */
public class SetsTest {

    /**
     * Sets工具类的常用方法
     * 并集 / 交集 / 差集 / 分解集合中的所有子集 /求两个集合的笛卡尔积
     *
     * Lists工具类的常用方法
     * 反转 / 拆分
     */

    private static final Set set1 =
            Sets.newHashSet(1, 2);

    private static final Set set2 =
            Sets.newHashSet(4, 5);

    // 并集
    @Test
    public void union(){
        Set<Integer> set = Sets.union(set1, set2);

        System.err.println(set);
    }

    // 交集
    public void intersection(){
        Set<Integer> set = Sets.intersection(set1, set2);

        System.err.println(set);
    }

    // 差集 : 如果元素属于A而且不属于B
    @Test
    public void difference(){
        Set<Integer> set = Sets.difference(set1, set2);

        System.err.println(set);

        // 相对差集 : 属于A而且不属于B 或者属于B而且不属于A
        set = Sets.symmetricDifference(set1, set2);

        System.err.println(set);

    }

    // 将一个集合 拆成了它所有可能元素的子集合
    @Test
    public void powerSet(){
        Set<Set<Integer>> powerSet = Sets.powerSet(set1);
        System.err.println(JSON.toJSONString(powerSet, true));
    }

    // 返回两个集合的笛卡尔积
    @Test
    public void cartesianProduct(){
        Set<List<Integer>> set = Sets.cartesianProduct(set1, set1);

        System.err.println(JSON.toJSONString(set, true));
    }


    // 将集合中元素拆分成 n 个元素为一个集合的子集序列
    @Test
    public void partition(){
        List<Integer> list =
                Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);

        List<List<Integer>> partition = Lists.partition(list, 3);

        System.err.println(JSON.toJSONString(partition, true));
    }

    // 反转
    @Test
    public void reverse(){
        List<Integer> list = Lists.newLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Integer> newList = Lists.reverse(list);
        System.err.println(JSON.toJSONString(newList, true));
    }

}
