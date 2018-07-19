package com.karl.debugger.ui.utils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * 类工具
 *
 * @author karl
 * @date 2018/7/19
 */
public class ClassUtils {
    /**
     * 获取注解信息
     * @param anns 注解对象
     * @return
     */
    public static List<String> getAnnotationTypes(Annotation[] anns) {
        if(anns == null || anns.length == 0) {
            return null;
        }

        List<String> res = new ArrayList<>(anns.length);
        for(Annotation ann : anns) {
            res.add(ann.annotationType().getName());
        }
        if(res.size() == 0) {
            return null;
        }
        return res;
    }

    /**
     * 根据类获取类名
     * @param classes
     * @return
     */
    public static List<String> getClassNames(Class<?>[] classes) {
        if(classes == null || classes.length == 0) {
            return null;
        }

        List<String> res = new ArrayList<>(classes.length);
        for(Class<?> clz : classes) {
            res.add(clz.getName());
        }
        return res;
    }
}
