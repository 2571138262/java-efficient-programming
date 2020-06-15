package com.baixiaowen.javaefficientprogramming.lambda.test;

import lombok.Data;

@Data
public class Lombok {

    private String a;


    public static void main(String[] args) {
        System.err.println(new Lombok().getA());
    }
}
