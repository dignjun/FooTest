package com.example.thread_demo.chap2;

/**
 * Created by DINGJUN on 2020.04.15.
 * 锁的自动释放
 *  -程序异常，持有的锁自动释放
 *
 * 同步不具有继承性
 *  -也就是父类是同步的方法，子类重写了这个方法，子类的方法没有【同步】关键字就不是同步方法
 *
 * 同步方法的缺点，就是整个对象都被锁定，如果存在多个方法是同步的，那么执行效率将会大大降低
 *
 * 这里测试同步代码块的使用
 *
 */
public class Demo4 {
    public static void main(String[] args) {
        Demo4Service ser = new Demo4Service();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                ser.foo();
            }
        };
        t2.setName("A");
        Thread t1 = new Thread() {
            @Override
            public void run() {
                ser.foo();
            }
        };
        t1.setName("B");

        t1.start();
        t2.start();
    }
}

// 通过同步代码块使多线程顺序执行，注意这里的是锁是当前对象
// 通过this做锁和同步方法的方式效果完全一致，不推荐使用
class Demo4Service {
    public void foo() {
        try {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + "开始于" + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "结束于" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

