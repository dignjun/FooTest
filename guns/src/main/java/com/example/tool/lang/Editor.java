package com.example.tool.lang;

/**
 * 编辑器接口，常用于对集合中的元素做统一的编辑
 * 编辑器用两个作用：1.如果返回的值为null，表示此值被抛弃 2.对对象做修改
 * @param <T>
 */
public interface Editor<T> {
    /**
     * 修改过滤后的结果
     *
     * @param t 被过滤的对象
     * @return 修改后的对象，如果被过滤返回<code>null</code>
     */
    public T edit(T t);
}
