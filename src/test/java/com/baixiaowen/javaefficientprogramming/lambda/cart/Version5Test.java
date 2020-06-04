package com.baixiaowen.javaefficientprogramming.lambda.cart;

import com.alibaba.fastjson.JSON;
import com.baixiaowen.javaefficientprogramming.lambda.cart.strategy.SkuPredicate;
import com.baixiaowen.javaefficientprogramming.lambda.cart.strategy.impl.SkuTotalPricePredicate;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Version5Test {

    @Test
    public void filterSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        // 过滤商品单价小于1000的商品
        List<Sku> result
                = CartService.filterSkus(cartSkuList, new SkuPredicate() {
            @Override
            public boolean test(Sku sku) {
                return sku.getSkuPrice() < 1000;
            }
        });

        System.err.println(JSON.toJSONString(result, true));
    }

}