package com.baixiaowen.javaefficientprogramming.lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Version1Test {

    @Test
    public void filterElectroniceSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        // 查找购物车中数码类商品
        List<Sku> result = CartService.filterElectroniceSkus(cartSkuList);
        System.err.println(JSON.toJSONString(result, true));
    }

}