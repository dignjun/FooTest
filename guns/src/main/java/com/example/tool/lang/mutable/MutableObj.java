package com.example.tool.lang.mutable;

import java.io.Serializable;

/**
 * 可变object类型
 * @author DINGJUN
 * @date 2019.03.12
 */
public class MutableObj<T> implements Mutable<T>, Serializable {
    private static final long serialVersionUID = -464493129773743673L;

    private T value;

    /**
     * 构造，空值
     */
    public MutableObj() {
        super();
    }

    /**
     * 构造
     *
     * @param value 值
     */
    public MutableObj(final T value) {
        super();
        this.value = value;
    }

    // -----------------------------------------------------------------------
    @Override
    public T get() {
        return this.value;
    }

    @Override
    public void set(final T value) {
        this.value = value;
    }

    // -----------------------------------------------------------------------
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getClass() == obj.getClass()) {
            final MutableObj<?> that = (MutableObj<?>) obj;
            return this.value.equals(that.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value == null ? 0 : value.hashCode();
    }

    // -----------------------------------------------------------------------
    @Override
    public String toString() {
        return value == null ? "null" : value.toString();
    }

}
