package com.example.thread_demo.chap1;

/**
 * Created by DINGJUN on 2020.04.13.
 * java实现多线程的方式2
 * 创建一个类，实现Runnable接口
 * 同样重写run方法
 * 实例化这个类，并同时作为构造形参传递给新创建的Thread类
 * 执行Thread的start方法（Thread方法本身就是实现了Runnable接口）
 *
 *
 */
public class Demo4 {
    public static void main(String[] args) {
        // 通过匿名类的方式创建一个Thread类和线程类Demo4Thread
        // 并调用Thread类的start方法
        new Thread(new Demo4Thread()) {}.start();

        System.out.println("main thread execute over");
    }
}

class Demo4Thread implements Runnable {

    @Override
    public void run() {
        System.out.println("child thread running");
    }
}