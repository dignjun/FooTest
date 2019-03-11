package com.example.tool.convert.impl;

import com.example.tool.convert.AbstractConverter;

import java.util.Currency;

/**
 * 货币{@link Currency} 转换器
 *
 */
public class CurrencyConverter extends AbstractConverter<Currency> {

    @Override
    protected Currency convertInternal(Object value) {
        return Currency.getInstance(value.toString());
    }

}
