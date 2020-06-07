package com.baixiaowen.javaefficientprogramming.lambda.test;

import java.util.function.Consumer;

public class Test {

    Consumer<String> consumer1 = (String str) -> str.length();

    public void test(){
        consumer1.accept("aaa");
    }

    public static void run(){
        System.err.println("这是测试的Runnable");
    }

    public static void main(String[] args) {
        Runnable runnable = Test::run;
        new Thread(runnable).start();
    }
}