package com.baixiaowen.javaefficientprogramming.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 饱和策略
 */
public class PolicyTest {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2,
            3,
            60L,
            TimeUnit.SECONDS,
            // 有界阻塞队列
            new LinkedBlockingQueue<>(5)
    );

    /**
     * 任务类
     */
    class Task implements Runnable{
        /**
         * 任务名称
         */
        private String taskName;

        public Task(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.err.println("线程["+ Thread.currentThread().getName() +"]正在执行["+ this.taskName +"]任务...");
            try {
                Thread.sleep(1000L * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.err.println("线程["+ Thread.currentThread().getName() +"]已经执行完["+this.taskName+"]任务！！！");
        }
    }

    public void abortProlicyTest(){

    }

}