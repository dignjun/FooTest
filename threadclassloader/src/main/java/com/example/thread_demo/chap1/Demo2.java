package com.example.thread_demo.chap1;

/**
 * Created by DINGJUN on 2020.04.13.
 *
 * 线程的执行是随机的
 *
 * 执行的结果是：主线程和子线程在交替执行，并且不确定是谁先执行
 * 注意：如果是直接调用线程类的run方法，此时并不是启动一个线程
 * 而是普通的方法调用
 *
 */
public class Demo2 {
    public static void main(String[] args) {
        Demo2Thread thread = new Demo2Thread();
        thread.start();

        // main thread print number 1-10
        for (int i = 0; i< 10; i++) {
            System.out.println("main thread running");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * Thread线程类
 * print number 1-10
 */
class Demo2Thread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i<10; i++) {
            System.out.println("child thread method running");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
