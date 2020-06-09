package com.baixiaowen.javaefficientprogramming.stream.cases;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

/**
 * 案例二 ：【去重】- 标签管理功能模块。允许用户批量添加标签，后台需要对标签去重，并且需要防止数据库中存在同名的标签
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CaseTwo {

    /**
     * 用户请求的创建标签模型
     */
    @Data
    @AllArgsConstructor
    class TagReqDTO {
        /**
         * 标签名字
         */
        private String name;
        /**
         * 标签值：年龄
         */
        private Integer age;
    }

    /**
     * 从DB中查询出来的已经存在的标签名
     */
    List<String> tagListFromDB;
    /**
     * 用户请求的标签列表
     */
    List<TagReqDTO> tagListFromReq;

    @BeforeAll
    public void init() {
        // 数据库中存在的标签名列表
        tagListFromDB = Lists.newArrayList(
                "李四", "王五", "赵六");

        // 用户提交的
        tagListFromReq = Lists.newArrayList(
                new TagReqDTO("张三", 10),
                new TagReqDTO("李四", 30),
                new TagReqDTO("张三", 11));
    }

    @Test
    public void distinctTag(){
        tagListFromReq.stream()
                // TODO true:通过测试， 数据不过滤；false:未通过测试，数据被过滤
                .filter(tar -> !tagListFromDB.contains(tar.getName()))
                // TODO 使用equals对元素进行比较
                .distinct()
                .forEach(System.err::println);
    }

}
