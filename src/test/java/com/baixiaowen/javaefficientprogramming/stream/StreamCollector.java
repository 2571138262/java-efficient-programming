package com.baixiaowen.javaefficientprogramming.stream;

import com.alibaba.fastjson.JSON;
import com.baixiaowen.javaefficientprogramming.lambda.cart.CartService;
import com.baixiaowen.javaefficientprogramming.lambda.cart.Sku;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 常见预定义收集器使用
 */
public class StreamCollector {

    /**
     * 集合收集器
     */
    @Test
    public void toList(){

        List<Sku> list = CartService.getCartSkuList();

        List<Sku> result = list.stream()
                .filter(sku -> sku.getTotalPrice() > 100)

                .collect(Collectors.toList());

        System.err.println(JSON.toJSONString(result, true));

    }

    /**
     * 分组
     */
    @Test
    public void group(){
        List<Sku> list = CartService.getCartSkuList();

        // Map<分组条件， 结果集合>
        Map<Enum, List<Sku>> result = list.stream()
                .collect(Collectors.groupingBy(Sku::getSkuCategory));

        System.err.println(JSON.toJSONString(result, true));
    }

    /**
     * 分区
     */
    @Test
    public void partition(){
        List<Sku> list = CartService.getCartSkuList();

        Map<Boolean, List<Sku>> result = list.stream()
                .collect(Collectors.partitioningBy(sku -> sku.getTotalPrice() > 100));
        System.err.println(JSON.toJSONString(result, true));
    }

}