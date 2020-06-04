package com.baixiaowen.javaefficientprogramming.lambda.test;

import java.util.function.Consumer;

public class Test {

    Consumer<String> consumer1 = (String str) -> str.length();

    public void test(){
        consumer1.accept("aaa");
    }
}