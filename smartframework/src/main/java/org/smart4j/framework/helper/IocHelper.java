package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 *
 * Created by DINGJUN on 2018/5/5.
 */
public final class IocHelper {

    static {
        // 获取所有的bean类与bean实例之间的映射关系（简称bean map）
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            // 遍历bean map
            for (Map.Entry<Class<?>, Object> beanEntry :
                    beanMap.entrySet()) {
                // 从bean map中获取bean与bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                // 获取bean类定义的所有成员变量（简称bean field）
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    // 遍历 bean field
                    for (Field beanField :
                            beanFields) {
                        // 判断当前bean field是否带有inject注解
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            // 在bean map中获取bean field对应的实例
                            Class<?> beanFieldType = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldType);
                            if (beanFieldInstance != null) {
                                // 通过反射初始化bean field的值
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
