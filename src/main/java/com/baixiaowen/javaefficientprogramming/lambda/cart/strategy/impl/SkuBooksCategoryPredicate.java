package com.baixiaowen.javaefficientprogramming.lambda.cart.strategy.impl;

import com.baixiaowen.javaefficientprogramming.lambda.cart.Sku;
import com.baixiaowen.javaefficientprogramming.lambda.cart.SkuCategoryEnum;
import com.baixiaowen.javaefficientprogramming.lambda.cart.strategy.SkuPredicate;

/**
 * 对Sku的商品类型为图书类的判断标准
 */
public class SkuBooksCategoryPredicate implements SkuPredicate {

    @Override
    public boolean test(Sku sku) {
        return SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory());
    }
}