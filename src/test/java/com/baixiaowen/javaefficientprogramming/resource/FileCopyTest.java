package com.baixiaowen.javaefficientprogramming.resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * JDK7 之前的文件拷贝功能
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FileCopyTest {

    @Test
    public void copeFile(){
        /**
         * 1、创建输入/输出流
         * 2、执行文件拷贝，读取文件内容，写入到另一个文件中
         * 3、** 关闭文件流资源**
         */

        // 定义输入路径和输出路径
        String originUrl = "lib/FileCopyTest.java";
        String targetUrl = "targetTest/target.txt";

        FileInputStream originFileInputStream = null;
        FileOutputStream targetFileOutputStream = null;

        try {
            originFileInputStream = new FileInputStream(originUrl);

            targetFileOutputStream = new FileOutputStream(targetUrl);

            // 读取的字节信息
            int content;

            // 迭代、读取/写入字节
            while ((content = originFileInputStream.read()) != -1) {
                targetFileOutputStream.write(content);
            }


        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (targetFileOutputStream != null){
                try {
                    targetFileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (originFileInputStream != null){
                try {
                    originFileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}