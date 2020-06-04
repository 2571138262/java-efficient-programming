package com.baixiaowen.javaefficientprogramming.lambda.cart.strategy.impl;

import com.baixiaowen.javaefficientprogramming.lambda.cart.Sku;
import com.baixiaowen.javaefficientprogramming.lambda.cart.strategy.SkuPredicate;

/**
 * 对Sku的总价书否超过2000作为判断标准
 */
public class SkuTotalPricePredicate implements SkuPredicate {

    @Override
    public boolean test(Sku sku) {
        return sku.getTotalPrice() > 2000;
    }
}