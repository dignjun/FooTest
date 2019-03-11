package com.example.tool.convert.impl;

import com.example.tool.convert.AbstractConverter;

import java.util.UUID;

/**
 * UUID对象转换器转换器
 *
 */
public class UUIDConverter extends AbstractConverter<UUID> {

    @Override
    protected UUID convertInternal(Object value) {
        return UUID.fromString(convertToStr(value));
    }

}