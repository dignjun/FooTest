package com.example.thread_demo.chap1;

/**
 * Created by DINGJUN on 2020.04.13.
 * java多线程编程实现方式1
 * 1.创建一个类继承Thread类
 * 2.重写Thread的run方法
 * 3.实例化线程类，并调用start方法启动一个这个线程
 */
public class Demo1 {
    public static void main(String[] args) {

        Demo1Thread thread = new Demo1Thread();
        thread.start();

        System.out.println("主线程运行结束");
    }
}

class Demo1Thread extends Thread {
    @Override
    public void run() {
        System.out.println("子线程运行了");
    }
}
