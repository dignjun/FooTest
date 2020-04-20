package com.example.thread_demo.chap2;

/**
 * Created by DINGJUN on 2020.04.16.
 * 同步代码块中锁对象使用【this】和同步方法的效果相同（缺点也是相同的）
 *
 * 所以一般同步代码块中的锁使用任意对象作为锁
 *
 * 多个线程同时执行synchronized(x) 是将x对象本身作为【对象锁】
 *
 * 1.多个线程同时执行【同步代码块】时呈同步效果
 *
 * 2.其他线程执行x对象中的synchronized同步方法时呈同步效果
 *
 * 3.其他线程还行x对象中的synchronized(this)代码块也是同步效果
 *
 * synchronized也可以使用在静态方法上，锁是当前*.java文件对应的Class进行加锁
 */
public class Demo5 {
    public static void main(String[] args) {
        Demo5Service service = new Demo5Service();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                service.foo4();
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                service.foo3();
            }
        };
        Thread t3 = new Thread() {
            @Override
            public void run() {
                service.foo2();
            }
        };
        Thread t4 = new Thread() {
            @Override
            public void run() {
                service.foo1();
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        /**
         * 测试结果应该是方法3,4是并发执行
         * 方法1，2是同步执行
         */
    }
}

/**
 * 测试this方法块锁和任意锁对象
 */
class Demo5Service {
    // 任意锁对象
    private Object lockObject = new Object();

    public void foo1() {
        try {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + "1开始于:" + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "1结束于:" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void foo2() {
        try {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + "2开始于:" + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "2结束于:" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void foo3() {
        try {
                System.out.println(Thread.currentThread().getName() + "3开始于:" + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "3结束于:" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void foo4() {
        try {
            System.out.println(Thread.currentThread().getName() + "4开始于:" + System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "4结束于:" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
