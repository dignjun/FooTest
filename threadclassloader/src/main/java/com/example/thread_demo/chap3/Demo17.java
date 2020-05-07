package com.example.thread_demo.chap3;

/**
 * Created by DINGJUN on 2020.05.07.
 *
 * 测试join（long） 和 sleep（long）的区别
 *
 */
public class Demo17 {
    public static void main(String[] args) {
        Demo17Thread t1 = new Demo17Thread();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (t1) {
                    t1.start();
                    try {
//                        Thread.sleep(7000); // 其实持有锁和这里的sleep没什么关系（可能是等待7秒，而子线程是5秒的关系。），而是这个同步方法持有的t1对象，而且这个同步方法一直没有结束，所以其他线程调用t1的同步方法会被阻塞
                        t1.join(); // 同样是等待子线程执行完的join()方法，它会释放同步代码块的t1的锁定，而让其他线程执行其中的同步方法
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int i = 0;
                    while (true) {
                        i++;
                        System.out.println(i);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                t1.foo();
            }
        }).start();
    }
}

class Demo17Thread extends Thread{
    @Override
    public void run() {
        System.out.println("线程A开始于" + System.currentTimeMillis());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程A结束于" + System.currentTimeMillis());
    }

    synchronized public void foo() {
        System.out.println("方法执行时间" + System.currentTimeMillis());
    }
}