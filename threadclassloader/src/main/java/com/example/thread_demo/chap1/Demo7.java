package com.example.thread_demo.chap1;

/**
 * Created by DINGJUN on 2020.04.13.
 *
 * 线程类的常用api-currentThread
 *
 * main方法是名为main的线程调用
 * 线程类构造方法是被main线程调用
 * run方法是被Thread-0的线程调用，run方法是自动被调用的方法
 */
public class Demo7 {
    public static void main(String[] args) {
        Demo7Thread t = new Demo7Thread();
        t.start();
        System.out.println("main方法：" + Thread.currentThread().getName());
    }
}

class Demo7Thread extends Thread {
    public Demo7Thread(){
        System.out.println("线程构造方法：" + Thread.currentThread().getName());
    }
    @Override
    public void run() {
        System.out.println("线程run方法 " + Thread.currentThread().getName());
    }
}
