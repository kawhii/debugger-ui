package com.karl.debugger.ui.utils;

import java.util.Date;

/**
 * 属性工具
 *
 * @author karl
 * @date 2018/8/4
 */
public class PropertiesUtil {
    /**
     * 解析字符串
     * @param value
     * @param clazz
     * @return
     */
    public static Object parseValueFromString(String value, Class<?> clazz) {
        return parseValue(value, clazz);
    }

    /**
     * 解析值为对象
     * @param value
     * @param clazz
     * @return
     */
    public static Object parseValue(Object value, Class<?> clazz) {
        try {
            if (value == null) {
                if (clazz.isPrimitive()) {
                    if (boolean.class.isAssignableFrom(clazz)) {
                        return false;
                    }
                    if (byte.class.isAssignableFrom(clazz)) {
                        return 0;
                    }
                    if (char.class.isAssignableFrom(clazz)) {
                        return 0;
                    }
                    if (short.class.isAssignableFrom(clazz)) {
                        return 0;
                    }
                    if (int.class.isAssignableFrom(clazz)) {
                        return 0;
                    }
                    if (float.class.isAssignableFrom(clazz)) {
                        return 0f;
                    }
                    if (long.class.isAssignableFrom(clazz)) {
                        return 0L;
                    }
                    if (double.class.isAssignableFrom(clazz)) {
                        return 0d;
                    }
                }
                return null;
            }
            if (Integer.class.isAssignableFrom(clazz) || int.class.isAssignableFrom(clazz)) {
                return Integer.valueOf(value.toString());
            }
            if (Float.class.isAssignableFrom(clazz) || float.class.isAssignableFrom(clazz)) {
                return Float.valueOf(value.toString());
            }
            if (Long.class.isAssignableFrom(clazz) || long.class.isAssignableFrom(clazz)) {
                return Long.valueOf(value.toString());
            }
            if (Double.class.isAssignableFrom(clazz) || double.class.isAssignableFrom(clazz)) {
                return Double.valueOf(value.toString());
            }
            if (Short.class.isAssignableFrom(clazz) || short.class.isAssignableFrom(clazz)) {
                return Short.valueOf(value.toString());
            }
            if (Byte.class.isAssignableFrom(clazz) || byte.class.isAssignableFrom(clazz)) {
                return Byte.valueOf(value.toString());
            }
            if (Boolean.class.isAssignableFrom(clazz) || boolean.class.isAssignableFrom(clazz)) {
                return ("true".equals(value.toString()) || "1".equals(value.toString())) ? true : false;
            }
            if (String.class.isAssignableFrom(clazz)) {
                return value.toString();
            }
            if (Date.class.isAssignableFrom(clazz)) {
                return DateUtils.toDate(value);
            }
            return value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
