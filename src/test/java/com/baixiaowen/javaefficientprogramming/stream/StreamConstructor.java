package com.baixiaowen.javaefficientprogramming.stream;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 流的四种构建形式
 */
public class StreamConstructor {

    /**
     * 1、由值创建流
     */
    @Test
    public void streamFromValue() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        stream.forEach(System.err::println);
    }

    /**
     * 2、由数组创建流
     */
    @Test
    public void streamFromArrays(){
        int[] numbers = {1, 2, 3, 4, 5};
        IntStream stream = Arrays.stream(numbers);
        stream.forEach(System.err::println);
    }

    /**
     * 3、由文件生成流
     * @throws IOException
     */
    @Test
    public void streamFromFile() throws IOException {
        Stream<String> stream = Files.lines(Paths.get("/Users/ngnice/projects/Baixiaowen" +
                "/java高效编程/java-efficient-programming/src/test" +
                "/java/com/baixiaowen/javaefficientprogramming/stream" +
                "/StreamConstructor.java"));
        stream.forEach(System.err::println);
    }

    /**
     * 4、由函数生成流(无限流)
     */
    @Test
    public void streamFromFunction(){
        // 后一个元素基于上一个元素生成
//        Stream<Integer> stream = Stream.iterate(0, n -> n + 2);

        // 后一个元素不依赖上一个元素生成
        Stream<Double> stream = Stream.generate(Math::random);

        stream.limit(100).forEach(System.err::println);
    }

}