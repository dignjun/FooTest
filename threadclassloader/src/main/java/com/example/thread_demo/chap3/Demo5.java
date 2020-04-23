package com.example.thread_demo.chap3;

/**
 * Created by DINGJUN on 2020.04.22.
 *
 * 调用notify方法一次只随机通知一个线程进行唤醒
 *
 * 调用notifyAll可以唤醒所有睡眠的线程
 *
 * 注意：wait和notify以及notifyAll都需要在同步代码块中进行执行，否则会有【非法状态】异常抛出
 *
 * 留意wait和notify的使用的注意条件可以知道：
 *
 *  1.首先wait和notify以及notifyAll所在的代码块需要获取到锁对象
 *
 *  2.wait方法会释放锁
 *
 *  3.notify方法不会释放锁，直到代码块执行结束或者方法执行执行结束
 *
 */
public class Demo5 {
    public static void main(String[] args) throws InterruptedException {
        // 锁对象
        Object obj = new Object();
        // 四个线程执行业务方法
        for (int i = 0; i < 4; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Demo5Service service = new Demo5Service();
                    service.foo(obj);
                }
            }).start();
        }

        // 等待一秒，确定所有的子线程都已经开始执行并处于wait的状态
        Thread.sleep(1000);
        synchronized (obj) {
            obj.notify(); // 随机唤醒一个线程执行 。 wait和notify都要在同步代码块中执行，否则会有IllegalMonitorStateException异常抛出
            Thread.sleep(2000);
            obj.notifyAll(); // 唤醒所有等待的线程
        }
    }
}

// 业务代码持有特定的锁执行
class Demo5Service {
    public void foo (Object lock) {
        try {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "等待前执行");
                lock.wait();
                System.out.println(Thread.currentThread().getName() + "等待后执行");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
