package com.example.tool.thread.threadlocal;

/**
 * 带有Name标识的 {@link ThreadLocal}，调用toString返回name
 * @author DINGJUN
 * @date 2019.03.12
 */
public class NamedThreadLocal<T> extends ThreadLocal<T> {
    private final String name;

    /**
     * 构造
     *
     * @param name 名字
     */
    public NamedThreadLocal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
