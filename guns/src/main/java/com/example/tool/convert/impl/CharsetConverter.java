package com.example.tool.convert.impl;

import com.example.tool.convert.AbstractConverter;
import com.example.tool.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * 编码对象转换器
 * @author Looly
 *
 */
public class CharsetConverter extends AbstractConverter<Charset> {

    @Override
    protected Charset convertInternal(Object value) {
        return CharsetUtil.charset(convertToStr(value));
    }

}
