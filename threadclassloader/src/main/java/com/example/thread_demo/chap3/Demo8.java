package com.example.thread_demo.chap3;

/**
 * Created by DINGJUN on 2020.04.23.
 *
 * wait条件发生改变。其实也就是锁变了
 *
 * 锁变了，那么在等待线程是不会被已经变了的锁唤醒的。
 *
 */
public class Demo8 {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (Demo8Service.lock) {
                        System.out.println("等待前");
                        Demo8Service.lock.wait();
                        System.out.println("等待后");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(200);
        Demo8Service.lock = "b";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (Demo8Service.lock) {
                        System.out.println("唤醒前");
                        Demo8Service.lock.notify();
                        System.out.println("唤醒后");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

class Demo8Service {
    public static String lock = "a";
}

