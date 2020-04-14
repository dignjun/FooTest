package com.example.thread_demo.chap1;

/**
 * Created by DINGJUN on 2020.04.14.
 *
 * yield 方法
 * 这个方法会使当前线程放弃CPU资源，将资源让给其他的任务去占用
 * 但是这个放弃时间不确定，而且即使放弃了，也可能马上有获取了CUP的时间片
 *
 * 线程的优先级
 * 1-10 优先级从低到高，创建线程的默认优先级是5，超出这个范围会有异常抛出
 * 线程的优先级具有继承性，如线程a启动线程b，线程b的优先级与线程a是一样的
 * 线程的优先级具有【随机性】，也就是高优先级的线程不一定每一次都优先执行
 *
 * 守护线程
 * Java中有两种线程，一种是用户线程，一种是守护线程。
 * 一般我们创建的线程是用户线程，手动设置setDemon(true)时用户线程才变成守护线程
 * 守护线程自动销毁。典型的例子就是垃圾回收线程，当进程中没有用户现成的时候，垃圾回收线程就没有必要存在了，会自动销毁
 *
 */
public class Demo11 {
    public static void main(String[] args) {
        T11 t11 = new T11();
        System.out.println(t11.getPriority());
        t11.start();
    }
}

class T11 extends Thread {
    @Override
    public void run() {
        int count = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i ++) {
            Thread.yield();
            count += i + 1;
        }
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start) + " 毫秒");
    }
}
