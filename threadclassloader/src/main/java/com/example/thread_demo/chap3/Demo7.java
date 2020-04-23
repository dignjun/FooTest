package com.example.thread_demo.chap3;

/**
 * Created by DINGJUN on 2020.04.22.
 *
 * 通知过早：线程通知时机造成的程序执行的混乱问题。
 *
 * 通知过早其实是在线程还未等待的时候，其他线程已经进行了通知，然后等待线程就一直等待下去了
 *
 * 此时可以通过一个标志附加来控制等待线程是否执行，唤醒线程在唤醒执行之后同时把标志置为false
 *
 */
public class Demo7 {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        Demo7Service service = new Demo7Service();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (object) {
                        while (service.isRun) {
                            System.out.println("等待前");
                            object.wait();
                            System.out.println("等待后");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (object) {
                        System.out.println("唤醒前");
                        object.notify();
                        System.out.println("唤醒后");
                        service.isRun = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // 测试一，此时因为notify先执行了，所以等待线程会一直等待下去
        // 测试二，添加附属标记逻辑，再次执行，等待线程则不会进行等待
        t2.start();
        Thread.sleep(200);
        t1.start();
    }
}

class Demo7Service {
    boolean isRun = true;
}
