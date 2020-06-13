package com.baixiaowen.javaefficientprogramming.threadpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 学习线程池为我们提供的阻塞队列
 *  1、有界的阻塞队列
 *  2、无界的阻塞对垒
 *  3、同步移交的阻塞队列
 */
public class QueueTest {

    @Test
    public void arrayBlockingQueue() throws InterruptedException {
        /**
         * 基于数组的有界阻塞队列，队列容量为10
         */
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

        /**
         * 循环向队列添加元素
         */
        for (int i = 0; i < 20; i++) {
            // 阻塞的插入
            queue.put(i);
            System.err.println("向队列中添加值 ：" + i);
        }
    }

    @Test
    public void lindBlockingQueue() throws InterruptedException {
        // 基于链表的有界 / 无界阻塞队列，队列容量为10
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        /**
         * 循环向队列添加元素
         */
        for (int i = 0; i < 20; i++) {
            // 阻塞的插入
            queue.put(i);
            System.err.println("向队列中添加值 ：" + i);
        }
    }

    @Test
    public void synchronousQueue() throws InterruptedException {
        /**
         * 同步移交阻塞队列
         */
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        // 插入值
        new Thread(() -> {
            try {
                queue.put(1);
                System.err.println("插入成功");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 删除值
//        new Thread(() -> {
//            try {
//                queue.take();
//                System.err.println("删除成功");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();


        Thread.sleep(1000);

    }

}