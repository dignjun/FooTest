package com.example.tool.convert.impl;

import com.example.tool.convert.AbstractConverter;
import com.example.tool.util.BooleanUtil;
import com.example.tool.util.StrUtil;

/**
 * 字符转换器
 *
 *
 */
public class CharacterConverter extends AbstractConverter<Character> {

    @Override
    protected Character convertInternal(Object value) {
        if (char.class == value.getClass()) {
            return Character.valueOf((char) value);
        } else if (value instanceof Boolean) {
            return BooleanUtil.toCharacter((Boolean) value);
        } else if (boolean.class == value.getClass()) {
            return BooleanUtil.toCharacter((boolean) value);
        } else {
            final String valueStr = convertToStr(value);
            if (StrUtil.isNotBlank(valueStr)) {
                return Character.valueOf(valueStr.charAt(0));
            }
        }
        return null;
    }

}
