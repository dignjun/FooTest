package com.example.thread_demo.chap3;

/**
 * Created by DINGJUN on 2020.05.07.
 *
 * join方法与异常
 *
 * join 和 interrupt 方法遇到，会抛出异常
 *
 * 但是并不影响进程，因为还有线程没有结束，线程继续执行
 *
 */
public class Demo15 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    System.out.println(i);
                    i++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.start();
                    t1.join();
                    System.out.println("t2正常结束");
                } catch (InterruptedException e) {
                    System.out.println("t2异常结束");
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                t2.interrupt();
            }
        });

        // 线程2等待线程1结束
        t2.start();

        Thread.sleep(4000);

        // 线程3跑线程2的异常
        t3.start();

    }
}
