package com.example.thread_demo.chap1;

/**
 * Created by DINGJUN on 2020.04.13.
 *
 * 线程的start执行顺序和线程的启动顺序不一致
 */
public class Demo3 {
    public static void main(String[] args) {
        /**
         * 创建三个线程，分别一个不同的数字，然后顺序启动，观察是否顺序打印
         */
        Thread t1 = new Demo3Thread(1);
        Thread t2 = new Demo3Thread(2);
        Thread t3 = new Demo3Thread(3);
        t1.start();
        t2.start();
        t3.start();
    }
}

/**
 * 创建一个线程类（其实也可以通过匿名的方式创建）
 *
 * 构造传递一个数字，用于线程执行打印
 */
class Demo3Thread extends Thread{
    private int val;
    public Demo3Thread(int val) {
        this.val = val;
    }
    @Override
    public void run() {
        System.out.println("val=" + val);
    }
}
