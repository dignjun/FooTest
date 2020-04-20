package com.example.thread_demo.chap2;

/**
 * Created by DINGJUN on 2020.04.16.
 * 死锁
 *
 * 多个线程在运行过程中因争夺资源而造成的僵局，此时只能手动解决
 *
 * volatile 关键字，保证成员变量的可见性
 * 和synchronize比较
 * 1.是线程同步的轻量级实现，性能比synchronize要好，它只能修饰变量
 * 2.多线程访问不会阻塞
 * 3.可以保证数据的可见性，不能保证原子性。而synchronize正好相反
 * 4.volatile解决多线程变量间的可见性，而synchronize解决多个线程之间的同步性
 *
 */
public class Demo6 {
    public static void main(String[] args) {
        // 创建一个线程运行业务类
        Demo6Service service = new Demo6Service();
        new Thread() {
            @Override
            public void run() {
                service.foo(); // 方法调用，如果flag没有被修改，则一直循环下去
            }
        }.start();
        // 线程等待0.2秒，确保线程已经运行到循环位置
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 修改业务类的停止标志
        service.flag = false;
        /**
         * 测试最后发现启动的线程并不能随着标志的改变而停止
         *
         * 原因是此时线程中运行的变量始终是堆变量的缓存，也就是说修改的值，
         * 对已经运行中的线程不可见
         *
         * 而加上volatile关键字之后就可以正常退出。
         */
    }
}

class Demo6Service {
    public /*volatile*/ boolean flag = true;
    public void foo() {
        System.out.println("foo 开始运行");
        while (flag) {}
        System.out.println("foo 运行结束");
    }
}
