package com.example.base_4;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 1.java程序天生就是多线程程序,因为执行main方法的是一个名称为main的线程,
 * 下面一下启动一个java程序有多少个线程在运行.
 * <p>
 * Created by DINGJUN on 2019.09.08.
 */
public class MultiThread {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName() + " " + threadInfo.getThreadState());
        }
        /**
         [6] Monitor Ctrl-Break RUNNABLE
         [5] Attach Listener RUNNABLE
         [4] Signal Dispatcher RUNNABLE
         [3] Finalizer WAITING
         [2] Reference Handler WAITING
         [1] main RUNNABLE  // 用户线程入口, java程序启动之后这个进程之中会有多个线程在同时运行
         */
    }
}
