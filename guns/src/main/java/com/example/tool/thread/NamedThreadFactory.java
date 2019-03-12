package com.example.tool.thread;

import com.example.tool.util.StrUtil;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程创建工厂类，此工厂可选配置
 * 1.自定义线程命名前缀
 * 2.自定义是否守护线程
 * @author DINGJUN
 * @date 2019.03.12
 */
public class NamedThreadFactory implements ThreadFactory {
    private final String prefix;
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final boolean isDeamon;
    private final Thread.UncaughtExceptionHandler handler;

    public NamedThreadFactory(String prefix, boolean isDeamon){
        this(prefix, null, isDeamon);
    }

    public NamedThreadFactory(String prefix, ThreadGroup threadGroup, boolean isDeamon) {
        this(prefix, threadGroup, isDeamon, null);
    }

    public NamedThreadFactory(String prefix, ThreadGroup threadGroup, boolean isDeamon, Thread.UncaughtExceptionHandler handler) {
        this.prefix = StrUtil.isBlank(prefix) ? "Djtool" : prefix;
        if(null == threadGroup) {
            threadGroup = ThreadUtil.currentThreadGroup();
        }
        this.group = threadGroup;
        this.isDeamon = isDeamon;
        this.handler = handler;
    }
    @Override
    public Thread newThread(Runnable r) {
        final Thread t = new Thread(this.group, r, StrUtil.format("{}{}", prefix, threadNumber.getAndIncrement()));
        // 守护线程
        if(false == t.isDaemon()) {
            if(isDeamon) {
                //原线程为非守护则设置为守护
                t.setDaemon(true);
            }
        } else if(false == isDeamon) {
            // 原线程为守护则还原为非守护
            t.setDaemon(false);
        }
        //异常处理
        if(null != this.handler) {
            t.setUncaughtExceptionHandler(handler);
        }
        //优先级
        if (Thread.NORM_PRIORITY != t.getPriority()) {
            // 标准优先级
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}


























