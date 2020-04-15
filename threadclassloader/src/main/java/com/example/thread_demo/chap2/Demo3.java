package com.example.thread_demo.chap2;

/**
 * Created by DINGJUN on 2020.04.15.
 * 方法锁是对象锁
 *
 * 方法锁的重入
 */
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        Ds ds = new Ds();
        // 两个线程分别运行两个方法,测试两个方法并不能并发执行
        new Thread(){
            @Override
            public void run() {
                ds.test1();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                ds.test2();
            }
        }.start();

        System.out.println("-------test one over------");
        Thread.sleep(5000);
        // 同一个线程可以同时调用多个同步的方法-锁重入 也支持在父子类继承中
        new Thread(){
            @Override
            public void run() {
                ds.test3();
            }
        }.start();
    }
}

class Ds {
    synchronized public void test1 () {
        System.out.println("test1 run, thread name=" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test1 run over");
    }

    synchronized public void test2() {
        System.out.println("test2 run, thread name=" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test2 run over");
    }

    synchronized public void test3() {
        System.out.println("test3 run");
        test2();
        System.out.println("test3 run over");
    }
}