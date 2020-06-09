package com.baixiaowen.javaefficientprogramming.stream.cases;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 案例一：【查找】- 班级中有20名学生，每名学生有5门课的考试成绩，其中缺考的科目分数字段为空。需要找出有缺考的学生都叫什么名字。
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CaseOne {

    /**
     * 考试成绩模型
     */
    @Data
    @AllArgsConstructor
    class ExamStudentScore{
        // 学生姓名
        private String studentName;
        // 成绩
        private Integer scoreValue;
        // 科目名称
        private String subject;
    }

    /**
     * 学生考试成绩
     */
    Map<String, List<ExamStudentScore>> studentMap;

    @BeforeAll
    public void init() {
        studentMap = new HashMap<>();

        List<ExamStudentScore> zsScoreList = new ArrayList<>();
        zsScoreList.add(
                new ExamStudentScore(
                        "张三",
                        30,
                        "CHINESE"));
        zsScoreList.add(
                new ExamStudentScore(
                        "张三",
                        40,
                        "ENGLISH"));
        zsScoreList.add(
                new ExamStudentScore(
                        "张三",
                        50,
                        "MATHS"));
        studentMap.put("张三", zsScoreList);

        List<ExamStudentScore> lsScoreList = new ArrayList<>();
        lsScoreList.add(
                new ExamStudentScore(
                        "李四",
                        80,
                        "CHINESE"));
        lsScoreList.add(
                new ExamStudentScore(
                        "李四",
                        null,
                        "ENGLISH"));
        lsScoreList.add(
                new ExamStudentScore(
                        "李四",
                        100,
                        "MATHS"));
        studentMap.put("李四", lsScoreList);

        List<ExamStudentScore> wwScoreList = new ArrayList<>();
        wwScoreList.add(
                new ExamStudentScore(
                        "王五",
                        null,
                        "CHINESE"));
        wwScoreList.add(
                new ExamStudentScore(
                        "王五",
                        null,
                        "ENGLISH"));
        wwScoreList.add(
                new ExamStudentScore(
                        "王五",
                        70,
                        "MATHS"));
        studentMap.put("王五", wwScoreList);
    }

    @Test
    public void findStudent() {
        studentMap.forEach((studentName, scoreList) -> {
            boolean bool = scoreList.stream().anyMatch(score -> {
                // TODO anyMatch 找到任意一条符合条件的数据后就停止
                System.err.println(score);
                return score.getScoreValue() == null;
            });
            if (bool) {
                System.err.println("此学生【" + studentName + "】有缺考情况");
            }
        });
    }

}