package com.baixiaowen.javaefficientprogramming.lombok;

import com.sun.istack.internal.NotNull;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * @Getter注解
 * 为属性生成get方法
 */
//@Data
public class GetterTest {

    @Getter
    private String field1;

    @Getter(
       value = AccessLevel.PRIVATE,
       onMethod_= {@NotNull}
    )
    private String field2;

}