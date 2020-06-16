package com.baixiaowen.javaefficientprogramming.lombok;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;

/**
 * @Builder和@Singular注解
 */
@Builder
@Data
public class BuilderTest {

    /**
     * 静态属性  不能赋值
     */
    private static String staticField;

    /**
     * final属性
     */
    private final String finalField;

    /**
     * 已经初始化的Final字段 不能赋值
     */
    private final String initFinalField = "已初始化的Final字段！";

    /**
     * 普通属性
     */
    private String field = "普通属性Field";


    /**
     * 集合类属性
     */
    @Singular
    private List<String> listFields;

    public static void main(String[] args) {
        BuilderTest builderTest = BuilderTest
                // builder创建一个可以链式赋值的对象
                .builder()

                // 为这个对象的"每个"字段赋值
                .finalField("手动赋值FinalField字段")
//                .field("手动赋值Field字段")
//                .listFields(new ArrayList<>())
                .listField("Baixiaowen")
                .listField("lisi")

                // build方法来创建对象，
                // TODO ： 完成了对象的创建，此时创建出来的对象，是不可变的！！！
                .build();

        System.err.println(JSON.toJSONString(builderTest, true));
    }

}