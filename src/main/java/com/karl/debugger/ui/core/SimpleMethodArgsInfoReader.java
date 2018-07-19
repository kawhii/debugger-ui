package com.karl.debugger.ui.core;

import com.karl.debugger.ui.model.dto.ArgsInfo;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

/**
 * 方法参数读取信息
 *
 * @author karl
 * @date 2018/7/19
 */
public class SimpleMethodArgsInfoReader implements IMethodArgsInfoReader {
    @Override
    public ArgsInfo read(TypeVariable<Method> variable) {
        return null;
    }
}
