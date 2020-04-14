package com.example.thread_demo.chap1;

/**
 * Created by DINGJUN on 2020.04.13.
 * <p>
 * 多线程开发中的成员变量与线程安全
 *
 * 不共享数据，每个线程只使用自己的变量，此时是没有线程安全问题的
 */
public class Demo5 {
    public static void main(String[] args) {
        // 创建三个线程，各自运行
        new Demo5Thread(){}.start();
        new Demo5Thread(){}.start();
        new Demo5Thread(){}.start();
    }
}

/**
 * 构建线程类
 * 含有一个成员变量
 * 线程任务是对成员变量进行自减并输出
 */
class Demo5Thread extends Thread {
    private int count = 5;

    @Override
    public void run() {
        while (count > 0) {
            count --;
            System.out.println(Thread.currentThread().getName() + ": count=" + count);
        }
    }
}