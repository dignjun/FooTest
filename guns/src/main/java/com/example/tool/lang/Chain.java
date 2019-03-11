package com.example.tool.lang;

/**
 * 责任链接口
 * @param <E>
 * @param <T>
 */
public interface Chain<E, T> extends Iterable<E> {
    /**
     * 加入责任链
     * @param element 责任链新的环节元素
     * @return this
     */
    T addChain(E element);
}
