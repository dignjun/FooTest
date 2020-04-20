package com.example.thread_demo.chap3;

/**
 * Created by DINGJUN on 2020.04.20.
 * 使用wait方法等待一个线程
 * 使用notify方法唤醒一个线程
 */
public class Demo3 {
    public static void main(String[] args) {
        String s = new String();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                synchronized (s) {
                    System.out.println(Thread.currentThread().getName() + ", 睡眠前，" + System.currentTimeMillis());
                    try {
                        s.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ", 睡眠后，" + System.currentTimeMillis());
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                synchronized (s) {
                    System.out.println(Thread.currentThread().getName() + ", 唤醒前，" + System.currentTimeMillis());
                        s.notify();
                    System.out.println(Thread.currentThread().getName() + ", 唤醒后，" + System.currentTimeMillis());
                }
            }
        };
        t1.start();
        try {
            Thread.sleep(1000); // 确定线程一已经睡眠
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
