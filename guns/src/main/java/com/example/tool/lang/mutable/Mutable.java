package com.example.tool.lang.mutable;

/**
 * 提供可变值类型接口
 *
 * @author DINGJUN
 * @date 2019.03.12
 */
public interface Mutable<T> {
    /**
     * 获得原始值
     * @return 原始值
     */
    T get();

    /**
     * 设置值
     * @param value 值
     */
    void set(T value);

}
