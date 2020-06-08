package com.baixiaowen.javaefficientprogramming.stream;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 归约与汇总操作
 */
public class ReduceAndCollectTest {

    /**
     * 统计所有订单下的金额和商品数量
     */
    @Test
    public void reduceTest() {

        /**
         * 订单对象
         */
        @Data
        @AllArgsConstructor
        class Order {
            // 订单编号
            private Integer Id;
            // 商品数量
            private Integer productCount;
            // 消费总金额
            private Double totalAmount;
        }

        // 数据准备
        ArrayList<Order> list = Lists.newArrayList();
        list.add(new Order(1, 2, 25.12));
        list.add(new Order(2, 5, 257.23));
        list.add(new Order(3, 3, 23332.12));

        /**
         * 传统计算方式  --  计算商品的平均价格
         * 1、计算商品数量
         * 2、计算消费总金额
         */

        /**
         * 通过reduce，一步汇总商品数量和总金额
         */
        Order order = list.stream()
                // 并行执行
                .parallel()
                // 归约
                .reduce(
                        // 初始化值
                        new Order(0, 0, 0.0),
                        // Stream中俩个元素的计算逻辑
                        (Order order1, Order order2) -> {
                            System.err.println("执行 计算逻辑 方法！！！");
                            int productCount = order1.getProductCount() + order2.getProductCount();
                            double totalAmount = order1.getTotalAmount() + order2.getTotalAmount();
                            return new Order(0, productCount, totalAmount);
                        },
                        // 并行情况下，多个并行结果如何合并
                        (Order order1, Order order2) -> {
                            System.err.println("执行 合并 方法");
                            int productCount = order1.getProductCount() + order2.getProductCount();
                            double totalAmount = order1.getTotalAmount() + order2.getTotalAmount();
                            return new Order(0, productCount, totalAmount);
                        });

        System.err.println(JSON.toJSONString(order, true));
    }

    /**
     * 每一个账号下的平均商品价格
     */
    @Test
    public void collectTest() {
        /**
         * 订单对象
         */
        @Data
        @AllArgsConstructor
        class Order {
            // 订单编号
            private Integer Id;
            // 用户账号
            private String account;
            // 商品数量
            private Integer productCount;
            // 消费总金额
            private Double totalAmount;
        }

        // 数据准备
        ArrayList<Order> list = Lists.newArrayList();
        list.add(new Order(1, "zhangxiaoxi", 2, 25.12));
        list.add(new Order(2, "zhangxiaoxi", 5, 257.23));
        list.add(new Order(3, "lisi", 3, 23332.12));

        /**
         * Map<用户账号, 订单(数量和金额)>
         */
        Map<String, Order> collect = list.stream()
                .parallel()
                .collect(
                        () -> {
                            System.err.println("执行 初始化容器 操作！！！");
                            return new HashMap<String, Order>();
                        },
                        (HashMap<String, Order> map, Order newOrder) -> {
                            System.err.println("执行 新元素添加到容器的操作");
                            String account = newOrder.getAccount();
                            // 新元素的account已经在map中存在了，将新订单数据累加上
                            if (map.containsKey(account)) {
                                Order order = map.get(account);
                                order.setProductCount(
                                        newOrder.getProductCount() + order.getProductCount()
                                );
                                order.setTotalAmount(
                                        newOrder.getTotalAmount() + order.getTotalAmount()
                                );
                            } else {
                                // 如果不存在，直接将新订单存入map
                                map.put(account, newOrder);
                            }
                        },
                        (HashMap<String, Order> map1, HashMap<String, Order> map2) -> {
                            System.err.println("执行 并行结果合并 操作！！！！");
                            map2.forEach((key, value) -> {
                                map1.merge(key, value, (order1, order2) -> {

                                    // TODO 注意： 一定要用map1做合并，因为最后collect返回map1
                                    return new Order(0, key, order1.getProductCount() + order2.getProductCount(),
                                            order1.getTotalAmount() + order2.getTotalAmount());
                                });
                            });
                        });
        System.err.println(JSON.toJSONString(collect, true));
    }


}