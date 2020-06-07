package com.baixiaowen.javaefficientprogramming.lambda.methodReference;

import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        A a = () -> System.err.println("A");
        A a1 = System.out::println;

        a.test();

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        list.stream().forEach(System.err::println);
    }


}

interface A {
    void test();
}

interface B {
    void test(String b);
}

interface C {
    String test();
}

interface D {
    String test(String d);
}

interface F {
    String test(String f1, String f2);
}