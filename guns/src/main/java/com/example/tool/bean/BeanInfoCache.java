package com.example.tool.bean;

import com.example.tool.lang.SimpleCache;

import java.beans.PropertyDescriptor;
import java.util.Map;

/**
 * Bean属性缓存<br>
 * 缓存用于防止多次反射造成的性能问题
 */
public enum BeanInfoCache {
    INSTANCE;

    private SimpleCache<Class<?>, Map<String, PropertyDescriptor>> pdCache = new SimpleCache<>();
    private SimpleCache<Class<?>, Map<String, PropertyDescriptor>> ignoreCasePdCache = new SimpleCache<>();

    /**
     * 获得属性名和{@link PropertyDescriptor}Map映射
     * @param beanClass Bean的类
     * @param ignoreCase 是否忽略大小写
     * @return 属性名和{@link PropertyDescriptor}Map映射
     */
    public Map<String, PropertyDescriptor> getPropertyDescriptorMap(Class<?> beanClass, boolean ignoreCase){
        return (ignoreCase ? ignoreCasePdCache : pdCache).get(beanClass);
    }

    /**
     * 加入缓存
     * @param beanClass Bean的类
     * @param fieldNamePropertyDescriptorMap 属性名和{@link PropertyDescriptor}Map映射
     * @param ignoreCase 是否忽略大小写
     */
    public void putPropertyDescriptorMap(Class<?> beanClass, Map<String, PropertyDescriptor> fieldNamePropertyDescriptorMap, boolean ignoreCase){
        (ignoreCase ? ignoreCasePdCache : pdCache).put(beanClass, fieldNamePropertyDescriptorMap);
    }

}
