package com.example.tool.builder;

/**
 * 建造者模式接口定义
 * @param <T>
 */
public interface Builder<T> {
    /**
     * 构建
     *
     * @return 被构建的对象
     */
    T build();
}
