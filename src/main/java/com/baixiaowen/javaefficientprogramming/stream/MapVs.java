package com.baixiaowen.javaefficientprogramming.stream;

import com.baixiaowen.javaefficientprogramming.lambda.cart.Sku;

import java.util.Optional;
import java.util.function.Function;

public class MapVs {
    public static String apply(Sku sku) {
        return Optional.ofNullable(sku.getSkuName()).orElseGet(() -> new String("白晓文"));
    }
}