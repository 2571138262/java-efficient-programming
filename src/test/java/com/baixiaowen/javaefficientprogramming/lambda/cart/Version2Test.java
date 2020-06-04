package com.baixiaowen.javaefficientprogramming.lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Version2Test {

    @Test
    public void filterSkusByCategory(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        // 根据指定类型查找购物车中的商品
        List<Sku> result = CartService.filterSkusByCategory(cartSkuList, SkuCategoryEnum.BOOKS);
        System.err.println(JSON.toJSONString(result, true));
    }

}