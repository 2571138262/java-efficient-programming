package com.baixiaowen.javaefficientprogramming.lambda.cart;

import com.alibaba.fastjson.JSON;
import com.baixiaowen.javaefficientprogramming.lambda.cart.strategy.impl.SkuTotalPricePredicate;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Version4Test {

    @Test
    public void filterSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        // 过滤商品总价大于2000的商品
        List<Sku> result
                = CartService.filterSkus(cartSkuList, new SkuTotalPricePredicate());

        System.err.println(JSON.toJSONString(result, true));
    }

}