package com.example.tool.convert.impl;

import com.example.tool.convert.AbstractConverter;

/**
 * 字符串转换器
 *
 */
public class StringConverter extends AbstractConverter<String> {

    @Override
    protected String convertInternal(Object value) {
        return convertToStr(value);
    }

}
