package com.karl.debugger.ui.core;

import com.karl.debugger.ui.ClassUtils;
import com.karl.debugger.ui.model.dto.ArgsInfo;

import java.lang.reflect.Parameter;

/**
 * 方法参数读取信息
 *
 * @author karl
 * @date 2018/7/19
 */
public class SimpleMethodArgsInfoReader implements IMethodArgsInfoReader {
    @Override
    public ArgsInfo read(Parameter parameter) {
        ArgsInfo info = new ArgsInfo();
        info.setArgName(parameter.getName())
                .setType(parameter.getType().getName());
        info.setAnnotations(ClassUtils.getAnnotationTypes(parameter.getAnnotations()));
        return info;
    }
}
