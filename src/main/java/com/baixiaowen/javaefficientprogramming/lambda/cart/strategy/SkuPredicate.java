package com.baixiaowen.javaefficientprogramming.lambda.cart.strategy;

import com.baixiaowen.javaefficientprogramming.lambda.cart.CartService;
import com.baixiaowen.javaefficientprogramming.lambda.cart.Sku;

/**
 *  Sku选择谓词接口
 */
public interface SkuPredicate {

    /**
     * 选择判断标准
     * @param sku
     * @return
     */
    boolean test(Sku sku);

}