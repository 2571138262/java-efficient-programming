package com.baixiaowen.javaefficientprogramming.resource.resourceclosevs;

import com.baixiaowen.javaefficientprogramming.lambda.file.FileConsumer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.*;

/**
 * 流资源关闭比较测试类
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ResourceCloseVs {

    /**
     * JDK1.7之后推出的资源流关闭的java语法糖
     * @param url
     * @param fileConsumer
     */
    @Test
    public void newFileHandle(String url,
                              FileConsumer fileConsumer){
        try (
            // 声明、创建文件的流读取
            FileInputStream fileInputStream = new FileInputStream(url);

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            // 定义行变量和内容line
            String line;
            StringBuilder stringBuilder = new StringBuilder();

            // 循环读取文件内容
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }

            // 调用函数式接口方法，将文件内容传递给lambda表达式，实现业务逻辑
            fileConsumer.fileHandler(stringBuilder.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 传统的资源关闭的方式
     * @param url
     * @param fileConsumer
     */
    @Test
    public void oldFileHandle(String url,
                              FileConsumer fileConsumer){
        // 声明
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        // 创建文件读取流
        try {
            fileInputStream = new FileInputStream(url);

            inputStreamReader = new InputStreamReader(fileInputStream);

            bufferedReader = new BufferedReader(inputStreamReader);

            // 定义行变量和内容line
            String line;
            StringBuilder stringBuilder = new StringBuilder();

            // 循环读取文件内容
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }

            // 调用函数式接口方法，将文件内容传递给lambda表达式，实现业务逻辑
            fileConsumer.fileHandler(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流资源操作
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}