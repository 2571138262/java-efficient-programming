package com.baixiaowen.javaefficientprogramming.stream;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * 归约与汇总操作
 */
public class ReduceAndCollectTest {

    @Test
    public void reduceTest(){

        /**
         * 订单对象
         */
        @Data
        @AllArgsConstructor
        class Order{
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

}