package com.example.thread_demo.chap2;

/**
 * Created by DINGJUN on 2020.04.15.
 * 1.局部变量是线程安全的
 * 局部变量存在于栈中，线程私有，所有没有线程安全问题
 *
 * 2.成员变量是不安全的
 * 成员变量存在于内存堆，线程操作的时候是栈中的变量持有的对堆中数据的引用，修改的是同一份数据
 *
 */
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        S s = new S();
        T t = new T(s);
        T2 t2 = new T2(s);
        t.start();
        t2.start();
        // 运行的结果是a对应100，b对应200

        Thread.sleep(5000);
        System.out.println("------第一个测试结束-----");

        S2 s2 = new S2();
        T3 t3 = new T3(s2);
        T4 t4 = new T4(s2);
        t3.start();
        t4.start();
        // 运行结束结果是 a = 200 ， b = 200
        /**
         * 因为两个线程跑的同一个变量，第一个变量在设置值之后，并没有立即输出
         * 此时等待（业务长情景）的过程另一个线程将变量设置为别的值时，此时相对于
         * 第一个线程来说他的变量在处理的整个业务过程中被别人修改了。
         *
         *  此时如果使用synchronized修饰方法（锁是整个对象，如果存在其他的
         *  方法使用此关键字，那么同样互斥），就不会存在这样的问题，
         */
    }
}

// 业务类
class S {
    public void add(String username) {
        int num = 0;
        if ("a".equals(username)) {
            num = 100;
            System.out.println("a set over");
            // 等待另一个线程修改num值（其实是办不到的事情）
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            num = 200;
            System.out.println("b set over");
        }
        System.out.println("username=" + username + ", num=" + num);
    }
}
// 线程一，执行业务一部分
class T extends Thread {
    private S s;
    public T (S s){
        this.s = s;
    }
    @Override
    public void run() {
        s.add("a");
    }
}
// 线程二，执行业务第二部分
class T2 extends Thread {
    private S s;
    public T2 (S s){
        this.s = s;
    }
    @Override
    public void run() {
        s.add("b");
    }
}

class S2 {
    int num = 0;
    synchronized public void add(String username) {
        if (username.equals("a")) {
            num = 100;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("aa set over");
        } else {
            num = 200;
            System.out.println("bb set over");
        }
        System.out.println("username=" + username + ", num=" + num);
    }
}

class T3 extends Thread {
    private S2 s2;
    public T3(S2 s2) {
        this.s2 = s2;
    }
    @Override
    public void run() {
        s2.add("a");
    }
}

class T4 extends Thread {
    private S2 s2;
    public T4(S2 s2) {
        this.s2 = s2;
    }
    @Override
    public void run() {
        s2.add("b");
    }
}