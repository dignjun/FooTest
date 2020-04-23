package com.example.thread_demo.chap3;

/**
 * Created by DINGJUN on 2020.04.22.
 *
 * wait(long)方法的使用测试
 *
 * sleep(long)与之比较：
 *  sleep(long)不会释放锁，而wait(long)会释放锁
 *
 */
public class Demo6 {
    public static void main(String[] args) throws InterruptedException {
        // 锁对象
        Object obj = new Object();

        // 创建两个测试线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (obj) {
                        System.out.println("进出入同步代码块" + System.currentTimeMillis());
                        obj.wait(4000);
                        System.out.println("结束同步代码块" + System.currentTimeMillis());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 如果等待线程没有在执行的时间内唤醒，那么会自动获取锁并且执行
//        Thread.sleep(8000);

        new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (obj) {
                        System.out.println("开始唤醒线程" + System.currentTimeMillis());
                        /**
                         * 如果在这里处理时间超过线程等待时间，线程也不能立刻执行，因为
                         * 等待超时的线程需要重新获取锁，才能继续执行，此时只能等待这个
                         * 线程执行结束才可以继续执行。
                         *
                         * 同时也证明了notify方法并不会释放锁，需要等到同步代码块或者同步方法执行结束才可以
                         */
                        Thread.sleep(5000);
                        obj.notify();
                        System.out.println("唤醒线程结束" + System.currentTimeMillis());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
