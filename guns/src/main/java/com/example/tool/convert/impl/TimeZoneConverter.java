package com.example.tool.convert.impl;

import com.example.tool.convert.AbstractConverter;

import java.util.TimeZone;

/**
 * TimeZone转换器
 *
 */
public class TimeZoneConverter extends AbstractConverter<TimeZone> {

    @Override
    protected TimeZone convertInternal(Object value) {
        return TimeZone.getTimeZone(convertToStr(value));
    }

}
