package com.baixiaowen.javaefficientprogramming.stream.cases;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 【分组】 - 设计一个对外提供服务的接口，支持调用方法传入多个账户编号查询订单信息
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CaseFour {

    /**
     * 订单信息类
     */
    @Data
    @AllArgsConstructor
    class Order {
        // 订单编号
        private Integer orderId;
        // 账户编号
        private String accountId;
    }

    /**
     * 模拟数据库查询
     *
     * @param accountIds
     * @return
     */
    public List<Order> selectFromDB(List<String> accountIds) {
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orderList.add(new Order(i, accountIds.get(i % accountIds.size())));
        }
        return orderList;
    }

    /**
     * 接口实现
     *
     * @param accountIds
     * @return
     */
    public Map<String, List<Order>> queryOrderByAccountIds(List<String> accountIds) {
        return Optional.ofNullable(selectFromDB(accountIds))
                .map(List::stream)
                .orElseGet(Stream::empty)

                // TODO group 分组功能
                .collect(Collectors.groupingBy(Order::getAccountId));
    }

    @Test
    public void test() {
        Map<String, List<Order>> stringListMap = queryOrderByAccountIds(Lists.newArrayList("张三", "李四", "王五"));
        System.err.println(JSON.toJSONString(stringListMap, true));
    }
}
