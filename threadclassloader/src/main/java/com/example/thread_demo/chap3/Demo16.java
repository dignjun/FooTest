package com.example.thread_demo.chap3;

/**
 * Created by DINGJUN on 2020.05.07.
 *
 * join(long) 方法的使用
 *
 * 主线程等待子线程相应的毫秒后继续执行
 *
 * join(2000) 和 sleep(2000)的区别：
 *  运行效果是相同的，但是同步的处理是不同的，sleep不会释放锁，join方法里面使用的是wait方法，所以会释放锁
 *
 */
public class Demo16 {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程睡眠了4秒");
            }
        });

        t.start();
        try {
            t.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程运行结束了");
    }
}
