package com.example.thread_demo.chap1;

/**
 * Created by DINGJUN on 2020.04.14.
 * 暂停线程
 * 暂停线程使用suspend
 * 启动线程使用resume方法
 *
 * suspend如果占用公共同步资源，会使其他线程无法访问公共同步对象
 *
 */
public class Demo10 {
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();
        Thread.sleep(1000);

        t.suspend(); // 暂停线程
        System.out.println("A=" + System.currentTimeMillis() + ", i=" + t.getI());
        Thread.sleep(1000);
        System.out.println("A=" + System.currentTimeMillis() + ", i=" + t.getI());

        t.resume(); // 恢复运行
        Thread.sleep(1000);
        System.out.println("B=" + System.currentTimeMillis() + ", i=" + t.getI());
        Thread.sleep(1000);
        System.out.println("B=" + System.currentTimeMillis() + ", i=" + t.getI());

        System.out.println("--------------------------");

        // 创建一个线程A运行同步方法，稍后同时运行另一个线程
        S s = new S();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                s.printString();
            }
        };
        t1.setName("A");
        t1.start();

        Thread.sleep(1000);
        Thread t2 = new Thread() {
            @Override
            public void run() {
                s.printString();
            }
        };
        t2.start();

    }
}

class T extends Thread {
    private long i = 0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {
        while(true) {
            i ++;
        }
    }
}

class S {
    synchronized public void printString() {
        System.out.println("线程开始");
        if ("A".equals(Thread.currentThread().getName())) {
            System.out.println("A线程永远suspend了");
            Thread.currentThread().suspend();
        }
        System.out.println("线程结束");
    }
}
