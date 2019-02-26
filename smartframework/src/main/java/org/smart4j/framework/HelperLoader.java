package org.smart4j.framework;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.helper.AopHelper;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.helper.IocHelper;
import org.smart4j.framework.util.ClassUtil;

/**
 * 加载响应的helper类
 * Created by DINGJUN on 2018/5/6.
 */
public final class HelperLoader {

    public static void init() {
        // 注意AopHelper需要在IocHelper之前加载
        Class<?>[] classList = {ClassHelper.class, BeanHelper.class, AopHelper.class, IocHelper.class, Controller.class};
        for (Class<?> cls :
                classList) {
            ClassUtil.loadClass(cls.getName(),true);
        }
    }
}
