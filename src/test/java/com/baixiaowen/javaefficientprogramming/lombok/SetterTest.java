package com.baixiaowen.javaefficientprogramming.lombok;


import lombok.AccessLevel;
import lombok.NonNull;
import lombok.Setter;

/**
 * @Setter注解
 * 为属性生成set方法
 */
public class SetterTest {

    @Setter
    private String field1;

    @Setter(
            value = AccessLevel.PRIVATE,
            onParam_ = {@NonNull}
    )
    private String field2;

}
