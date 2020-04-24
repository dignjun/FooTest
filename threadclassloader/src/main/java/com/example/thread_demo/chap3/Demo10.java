package com.example.thread_demo.chap3;

/**
 * Created by DINGJUN on 2020.04.23.
 *
 * 多生产者和多消费者的实现
 *
 * 1.使用notify和wait
 *
 * 2.使用notifyAll和wait
 *
 * 预期：通过方式1会产生所有的线程都是等待的情况，未出现。
 *      说明： 四个线程持有一个锁对象，也就是同步代码块同时只能有一个线程在执行，除非这个线程进入到了wait()方法，释放了锁的持有
 *            假设两个消费者线程此时等待，唤醒了一个生产者线程，结束后，它又唤醒一个生产者，此时所有的线程将等待
 *
 * 测试结果：并未出现所有线程都是等待的状态，但是有一个相似的问题，四个线程开始执行
 *      说明：两个消费者竞争到锁，等待。生产者生产完之后，唤醒一个线程，
 *      碰巧一个消费者消费，然后结束后，释放锁之后另一个消费者被唤醒，此时消费的变量还未被生产，导致了同一个变量被消费了两次
 *
 * notify和notifyAll测试结果相同。但，推荐多生产者多消费者使用notifyAll，防止潜在的【假死】状态。另外多次消费可以通过判断控制
 */
public class Demo10 {
    public static void main(String[] args) {
        // 1.锁
        Object lock = new Object();

        // 2.业务service
        Demo10Service service = new Demo10Service();

        // 3.多生产者消费者线程
        for (int i = 0; i < 2; i ++) {
            // 生产者
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            synchronized (lock) {
                                if (!"".equals(service.value)) {
                                    System.out.println("生产者 " + Thread.currentThread().getName() + " 等待中...");
                                    lock.wait();
                                }
                                System.out.println("生产者 " + Thread.currentThread().getName() + " 生产后");
                                service.value = System.currentTimeMillis() + "";
//                                lock.notify();
                                lock.notifyAll();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            // 消费者
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            synchronized (lock) {
                                if ("".equals(service.value)) {
                                    System.out.println("消费者 " + Thread.currentThread().getName() + " 等待中...");
                                    lock.wait();
                                }
                                if (!"".equals(service.value)) { // 防止同一个值多次消费的问题
                                    System.out.println("消费者 " + Thread.currentThread().getName() + " 消费后 :" + service.value);
                                }
                                service.value = "";
//                                lock.notify();
                                lock.notifyAll();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }
}

class Demo10Service {
    public volatile static String value = "";
}
