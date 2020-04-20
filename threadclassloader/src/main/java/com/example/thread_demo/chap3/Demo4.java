package com.example.thread_demo.chap3;

/**
 * Created by DINGJUN on 2020.04.20.
 *
 * wait方法遇到interrupt方法
 *
 * 线程是wait状态时，调用线程的interrupt方法会产生异常
 *
 * 1.执行完同步代码块就会释放对象锁
 * 2.执行同步代码块过程中，遇到异常而导致线程终止，锁也会被释放
 * 3.执行同步代码块的过程中，执行了锁所属对象的wait方法，这个线程会释放对象锁，
 *   同时此线程对象会进入到线程等待池中等待被唤醒
 *
 */
public class Demo4 {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                foo(lock);
            }
        };
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }

    public static void foo(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("准备开始等待");
                lock.wait();
                System.out.println("等待结束");
            }
        } catch (InterruptedException e) {
            System.out.println("出现异常，interrupted了");
            e.printStackTrace();
        }
    }
}
