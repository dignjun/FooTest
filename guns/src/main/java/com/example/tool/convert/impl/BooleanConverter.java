package com.example.tool.convert.impl;

import com.example.tool.convert.AbstractConverter;
import com.example.tool.util.BooleanUtil;

/**
 * 波尔转换器
 *
 */
public class BooleanConverter extends AbstractConverter<Boolean> {

    @Override
    protected Boolean convertInternal(Object value) {
        if(boolean.class == value.getClass()){
            return Boolean.valueOf((boolean)value);
        }
        String valueStr = convertToStr(value);
        return Boolean.valueOf(BooleanUtil.toBoolean(valueStr));
    }

}