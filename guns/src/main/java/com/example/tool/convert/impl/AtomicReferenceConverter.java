package com.example.tool.convert.impl;

import com.example.tool.convert.AbstractConverter;
import com.example.tool.convert.ConverterRegistry;
import com.example.tool.util.TypeUtil;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

/**
 * {@link AtomicReference}转换器
 *
 */
@SuppressWarnings("rawtypes")
public class AtomicReferenceConverter extends AbstractConverter<AtomicReference> {

    @Override
    protected AtomicReference<?> convertInternal(Object value) {

        //尝试将值转换为Reference泛型的类型
        Object targetValue = null;
        final Type paramType = TypeUtil.getTypeArgument(AtomicReference.class);
        if(null != paramType){
            targetValue = ConverterRegistry.getInstance().convert(paramType, value);
        }
        if(null == targetValue){
            targetValue = value;
        }

        return new AtomicReference<>(targetValue);
    }

}
