package com.baixiaowen.javaefficientprogramming.resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 基于JDK7之后实现政企关闭流资源的方式
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NewFileCopyTest {

    @Test
    public void copyFile(){

        // 先定义输出/输出路径
        String originjUrl = "lib/NewFileCopyTest.java";
        String targetUrl = "targetTest/newTarget.txt";

        // 初始化输入/输出流对象
        try(
                FileInputStream originFileInputStream = new FileInputStream(originjUrl);

                FileOutputStream targetFileOutputStream = new FileOutputStream(targetUrl);
                ){
            int content;

            // 迭代，拷贝数据
            while((content = originFileInputStream.read()) != -1){
                targetFileOutputStream.write(content);
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}