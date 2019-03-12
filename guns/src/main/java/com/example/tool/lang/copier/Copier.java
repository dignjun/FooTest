package com.example.tool.lang.copier;

/**
 * 拷贝接口
 * @author DINGJUN
 * @date 2019.03.12
 */
public interface Copier<T> {
    /**
     * 执行拷贝
     * @return 拷贝的目标
     */
    T copy();
}
