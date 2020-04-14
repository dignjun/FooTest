package com.example.thread_demo.chap1;

/**
 * Created by DINGJUN on 2020.04.14.
 *
 * 停止线程
 * 1.使用退出标识，也就是通过一个标识控制子线程是否执行结束（未执行结束其实子线程循环卡死）
 *
 * 2.stop方法强制结束线程，
 *
 * 3.使用interrupt方法中断线程，只是标记一下，通过Thread类的interrupted方法或者线程类的isInterrupted方法测试线程是否有这个标记
 * 两个方法结合使用
 * 主线程的interrupted方法可以清除中断状态。
 *
 */
public class Demo9 {
    public static void main(String[] args) throws InterruptedException {
        Demo9Thread t = new Demo9Thread();
        t.start();
        System.out.println("主线程等待两秒");
        Thread.sleep(2000);
        System.out.println("主线程两秒等待结束");
        t.stopThread();

        System.out.println("----------------------------");

        Demo9User user = new Demo9User();
        Demo9Thread2 t2 = new Demo9Thread2(user);
        t2.start();
        Thread.sleep(500);
        t.stopThread();
        System.out.println("username=" + user.getUsername() + ", password=" + user.getPassword());

        System.out.println("----------------------------");

        Demo9Thread3 t3 = new Demo9Thread3();
        t3.start();
        t3.interrupt();
        System.out.println("是否停止1？" + t3.isInterrupted());
        System.out.println("是否停止2？" + Thread.interrupted()); // 主线程方法会清除【中断标记】，如：第一次调用时true，第二次调用则是false

    }
}

/**
 * 1.使用停止标识
 */
class Demo9Thread extends Thread {
    private boolean flag = true;
    @Override
    public void run() {
        while (flag) {
            System.out.println("time=" + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("线程执行结束");
    }
    public void stopThread() {
        flag = false;
    }
}

/**
 * 2.通过stop方法停止线程，此方法已作废
 * 因为如果强制停止一个线程，可能使一些清理性的工作得不到完成
 * 另外一个原因是对锁定的对象进行【解锁】，导致数据得不到同步处理，出现数据不一致的问题
 *
 * 这个线程线程业务是设置用户的名称和密码（业务时间长，耗时10秒），但是线程经过5秒时被stop处理
 * 此时的用户名和密码是不一致的。
 */
class Demo9Thread2 extends Thread {
    private Demo9User user;
    public Demo9Thread2( Demo9User user) {
        this.user = user;
    }

    @Override
    public void run() {
        user.updateUsernameAndPassword("b", "bb");
    }
}

/**
 * User类，提供设置用户名和密码的方法，注意这个设置方法处理时间有点“长”
 */
class Demo9User {
    private String username = "a";
    private String password = "aa";

    public void updateUsernameAndPassword(String username, String password) {
        this.username = username;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

/**
 * 3.interrupt方法不会真正结束线程，而是在当前线程打上一个停止标记
 * Thread类提供了interrupted方法测试当前线程是否中断(主线程)，IsInterrupted方法测试线程是否已经中断
 */
class Demo9Thread3 extends Thread {
    @Override
    public void run() {
        for (int i=0;i<100;i++) {
            System.out.println("i=" + i);
        }
    }
}