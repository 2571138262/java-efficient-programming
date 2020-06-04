package com.baixiaowen.javaefficientprogramming.lambda.file;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FileServiceTest {

    @Test
    public void fileHandle() throws IOException {
        FileService fileService = new FileService();

        // 通过lambda表达式打印文件内容
        fileService.fileHandle("/Users/ngnice/projects/Baixiaowen" +
                "/java高效编程/java-efficient-programming/src/test/java" +
                "/com/baixiaowen/javaefficientprogramming/lambda/file" +
                "/FileServiceTest.java", fileContent -> {
            // 注释
            System.err.println(fileContent);
        });
    }

}