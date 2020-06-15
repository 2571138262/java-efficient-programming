package com.baixiaowen.javaefficientprogramming.threadpool;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 饱和策略
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PolicyTest {

    /**
     * 线程池
     */
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            // 核心线程数
            2,
            // 最大线程数
            3,
            // 存活时间
            60L,
            // 单位
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
                Thread.sleep(1000L * 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.err.println("线程["+ Thread.currentThread().getName() +"]已经执行完["+this.taskName+"]任务！！！");
        }
    }

    /**
     * 线程池的执行过程
     *
     * 2 个核心线程
     * 5 个任务队列
     * 3 个最大线程 1 个线程可用
     * 前2个任务，会占用2个核心线程
     * 第3个任务到第7个任务，会暂存到任务队列中
     * 第8个任务，会启动最大线程，去执行
     * 第9个任务，没有线程可以去执行 ---
     */
    // 终止策略
    @Test
    public void abortPolicyTest(){
        // 设置饱和策略为 终止策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        for (int i = 1; i <= 10; i++) {
            try {
                // 提交10个线程任务
                executor.execute(new Task("线程任务底：" + i));
            }catch (Exception e){
                System.out.println(e);
            }
        }
        // 关闭线程池
        executor.shutdown();
    }

    /**
     * 抛弃策略
     */
    @Test
    public void discardPolicyTest(){
        // 设置饱和策略为 抛弃策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 1; i <= 10; i++) {
            try {
                // 提交10个线程任务
                executor.execute(new Task("线程任务底：" + i));
            }catch (Exception e){
                System.out.println(e);
            }
        }
        // 关闭线程池
        executor.shutdown();
    }

    /**
     * 抛弃旧任务策略
     */
    @Test
    public void discardOldestPolicyTest(){
        // 设置饱和策略为 抛弃策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 1; i <= 10; i++) {
            try {
                // 提交10个线程任务
                executor.execute(new Task("线程任务底：" + i));
            }catch (Exception e){
                System.out.println(e);
            }
        }
        // 关闭线程池
        executor.shutdown();
    }

    /**
     * CallerRunsPolicy调用者运行策略
     */
    @Test
    public void callerRunsPolicyTest(){
        // 设置饱和策略为 抛弃策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 1; i <= 10; i++) {
            try {
                // 提交10个线程任务
                executor.execute(new Task("线程任务底：" + i));
            }catch (Exception e){
                System.out.println(e);
            }
        }
        // 关闭线程池
        executor.shutdown();
    }

    /**
     * 单元测试执行完，主线程等待100秒
     * @throws InterruptedException
     */
    @AfterAll
    public void after() throws InterruptedException{
        Thread.sleep(1000L * 10);
    }

}