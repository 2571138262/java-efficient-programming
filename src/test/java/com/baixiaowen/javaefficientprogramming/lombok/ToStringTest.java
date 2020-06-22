package com.baixiaowen.javaefficientprogramming.lombok;

import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.Test;

/**
 * @ToString注解
 * 生成toString方法
 */
@ToString(
        includeFieldNames = false,
//        exclude = {"field1"},
//        of = {"field1"},
        doNotUseGetters = false
)
public class ToStringTest {

    @Setter
    private String field1;

    @Setter
    private String field2;

    public String getField2() {
        System.err.println("调用get方法");
        return field2;
    }

    @Test
    public void test(){
        ToStringTest toStringTest = new ToStringTest();
        toStringTest.setField1("Bai");
        toStringTest.setField2("xiaowen");
        System.err.println(toStringTest.toString());
    }
}