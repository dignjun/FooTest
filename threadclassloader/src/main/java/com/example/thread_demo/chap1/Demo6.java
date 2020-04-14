package com.example.thread_demo.chap1;

/**
 * Created by DINGJUN on 2020.04.13.
 *
 * 多线程共享成员变量，此时存在线程安全问题
 * 通过多个线程跑同一个线程类实现测试。
 *
 * 测试的结果是，不同的线程可能会拿到相同的变量值
 *
 */
public class Demo6 {
    public static void main(String[] args) {
        // 创建一个线程类
        Demo6Thread t = new Demo6Thread();
        // 创建多个线程
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        t1.start();
        t2.start();
        t3.start();
    }
}

class Demo6Thread extends Thread {
    private int a = 5;
    @Override
    public void run() {
        while (a > 0) {
            a--;
            System.out.println(Thread.currentThread().getName() + ", a = "+ a);
        }
    }
}
