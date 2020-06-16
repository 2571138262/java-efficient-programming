package com.baixiaowen.javaefficientprogramming.lombok;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

/**
 * @Getter注解
 * 为属性生成get方法
 */
//@Data
public class GetterTest {

    @Getter(
            lazy = true
    )
    private final String field1 = "baixiaowen";

    @Getter(
       value = AccessLevel.PRIVATE,
       onMethod_= {@NonNull}
    )
    private String field2;

}