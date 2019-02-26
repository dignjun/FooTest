package org.smart4j.framework.annotation;

import java.lang.annotation.*;

/**
 * 切面注解
 *
 * Created by DINGJUN on 2018/5/6.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 注解
     */
    Class<? extends Annotation> value();
}
