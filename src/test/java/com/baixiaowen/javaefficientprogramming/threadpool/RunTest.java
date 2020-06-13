package com.baixiaowen.javaefficientprogramming.threadpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池工具类
 */
public class RunTest {

    @Test
    public void submitTest() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        /**
         * 利用Submit提交任务，接受任务的返回结果
         */
        Future<Integer> future = threadPool.submit(() -> {
            // submit方法接收的参数是Callable类型，Callable的run方法时允许抛出异常的，所以这里的Thread.sleep() 不需要捕获异常
            Thread.sleep(1000L * 10);
            return 2 * 5;
        });

        /**
         * 阻塞方法，直到任务有返回值，才向下执行
         */
        Integer integer = future.get();

        System.err.println("执行结果 : " + integer);
    }

    @Test
    public void executorTest(){
        // 创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();

        threadPool.execute(() -> {
            try {
                Thread.sleep(1000L * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Integer num = 2 * 5;
            System.err.println("执行结果 ：" + num);

        });
    }

}