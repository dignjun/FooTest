package com.example.tool.convert.impl;

import com.example.tool.convert.AbstractConverter;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * {@link AtomicBoolean}转换器
 *
 */
public class AtomicBooleanConverter extends AbstractConverter<AtomicBoolean> {

    @Override
    protected AtomicBoolean convertInternal(Object value) {
        if (boolean.class == value.getClass()) {
            return new AtomicBoolean((boolean) value);
        }
        if (value instanceof Boolean) {
            return new AtomicBoolean((Boolean) value);
        }
        final String valueStr = convertToStr(value);
        return new AtomicBoolean(BooleanUtil.toBoolean(valueStr));
    }

}
