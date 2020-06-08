package com.baixiaowen.javaefficientprogramming.lambda.methodReference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MethodReferenceTest {

    static class Sku{
        private String skuName;
        private Integer skuPrice;

        public Integer getSkuPrice() {
            return skuPrice;
        }

        public static int staticComparePrice(Sku sku1, Sku sku2){
            return sku1.getSkuPrice() - sku2.getSkuPrice();
        }

        public int instanceComparePrice(Sku sku){
            return this.getSkuPrice() - sku.getSkuPrice();
        }
    }

    class PriceComparator{
        public int instanceComparaPrice(Sku sku1, Sku sku2){
            return sku1.getSkuPrice() - sku2.getSkuPrice();
        }
    }

    public void test(){
        List<Sku> skuList = new ArrayList<>();

        skuList.sort(new Comparator<Sku>() {
            @Override
            public int compare(Sku o1, Sku o2) {
                return o1.skuPrice.compareTo(o2.skuPrice);
            }
        });

        skuList.sort((sku1, sku2) -> sku1.getSkuPrice() - sku2.getSkuPrice());

        // 类名::静态方法名
        skuList.sort(Sku::staticComparePrice);
        // 展开
        skuList.sort((Sku sku1, Sku sku2) -> {
            return Sku.staticComparePrice(sku1, sku2);
        });


        PriceComparator priceComparator = new PriceComparator();
        // 指向现有对象的实例方法
        // 对象::实例方法名
        skuList.sort(priceComparator::instanceComparaPrice);
        // 展开
        skuList.sort((Sku sku1, Sku sku2) -> {
            return priceComparator.instanceComparaPrice(sku1, sku2);
        });

        // 类名::实例方法名
        skuList.sort(Sku::instanceComparePrice);
        // 展开
        skuList.sort((Sku object, Sku sku) -> {
            return object.instanceComparePrice(sku);
        });


        // 指向构造方法
        Optional.ofNullable(skuList).orElseGet(ArrayList::new);


        skuList.sort(Comparator.comparing(sku -> sku.getSkuPrice()));
    }

}