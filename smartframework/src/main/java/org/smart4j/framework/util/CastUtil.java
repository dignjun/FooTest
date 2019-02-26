package org.smart4j.framework.util;



/**
 * 转型操作工具类
 * Created by DINGJUN on 2018/4/16.
 */
public class CastUtil {

    /**
     * 转换为String类型
     * @param obj
     * @return
     */
    public static String castString(Object obj) {
        return castString(obj,"");
    }

    /**
     * 转换为String类型（提供默认值）
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    /**
     * 转换为double类型
     * @param obj
     * @return
     */
    public static double castDouble(Object obj) {
        return castDouble(obj,0);
    }
    /**
     * 转换为double类型（提供默认值）
     * @param obj
     * @param defaultValue
     * @return
     */
    public static double castDouble(Object obj, double defaultValue) {
        double doubleValue = defaultValue;
        if(obj != null) {
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)) {
                try {
                    doubleValue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    /**
     * 转换为long类型
     * @param obj
     * @return
     */
    public static long castLong(Object obj) {
        return castLong(obj,0);
    }

    /**
     * 转换为long类型（提供默认值）
     * @param obj
     * @param defaultValue
     * @return
     */
    public static long castLong(Object obj,long defaultValue) {
        long value = defaultValue;
        if(obj != null) {
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)) {
                try {
                    value = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    /**
     * 转换为int类型
     * @param obj
     * @return
     */
    public static int castInt(Object obj) {
        return castInt(obj,0);
    }

    /**
     * 转换为int类型（提供默认值）
     * @param obj
     * @param defaultValue
     * @return
     */
    public static int castInt(Object obj, int defaultValue) {
        int intValue = defaultValue;
        if(obj != null) {
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)) {
                try {
                    intValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    /**
     * 转换为boolean类型
     * @param obj
     * @return
     */
    public static boolean castBoolean(Object obj) {
        return castBoolean(obj,false);
    }

    /**
     * 转换为boolean类型（可以指定默认值）
     * @param obj
     * @param defaultValue
     * @return
     */
    public static boolean castBoolean(Object obj, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if(obj != null) {
            String strValue = castString(obj);
            try {
                booleanValue = Boolean.parseBoolean(strValue);
            } catch (Exception e) {
                booleanValue = defaultValue;
            }
        }
        return booleanValue;
    }
}
