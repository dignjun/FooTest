package org.smart4j.chapter2.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类
 * Created by DINGJUN on 2018/4/16.
 */
public final class StringUtil {

    /**
     * 判断字符串是否为空
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        if(string != null) {
            string = string.trim();
        }
        return StringUtils.isEmpty(string);
    }

    /**
     * 判断字符串是否非空
     * @param string
     * @return
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }
}
