package com.baixiaowen.javaefficientprogramming.lombok;

import lombok.val;

import java.util.ArrayList;

/**
 * @val注解 作用范围是本地方法
 * 弱语言变量
 */
public class ValTest {

    private ValTest(){
        val field = "baixiaowen";

        val list = new ArrayList<String>();

        list.add("baixiaowen");
    }

}
