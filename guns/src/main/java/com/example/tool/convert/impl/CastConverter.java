package com.example.tool.convert.impl;

import com.example.tool.convert.AbstractConverter;
import com.example.tool.convert.ConvertException;

/**
 * 强转转换器
 *
 * @param <T> 强制转换到的类型
 */
public class CastConverter<T> extends AbstractConverter<T> {

    private Class<T> targetType;

    @Override
    protected T convertInternal(Object value) {
        // 由于在AbstractConverter中已经有类型判断并强制转换，因此当在上一步强制转换失败时直接抛出异常
        throw new ConvertException("Can not cast value to [{}]", this.targetType);
    }

    @Override
    public Class<T> getTargetType() {
        return this.targetType;
    }
}
