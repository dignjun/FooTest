package com.example.core.mutidatasource.annotion;

import java.lang.annotation.*;

/**
 * 多数据源标识
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataSource {

    String name() default "";

}
