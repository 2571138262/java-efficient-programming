package com.baixiaowen.javaefficientprogramming.Ideashortcut;

import java.io.Serializable;

public class ExtractTest implements Serializable {



    public static final String NAME = "zhangsan";
    private String nameField;

    public void print1(String nameParam){
        commonPrint(nameParam);
    }

    private void commonPrint(String nameParam) {
        String name = "张三";

        System.err.println(nameParam);
        System.err.println(nameParam);
        System.err.println(nameParam);
    }

    public void print2(String nameParam){
        commonPrint(nameParam);
    }

}
