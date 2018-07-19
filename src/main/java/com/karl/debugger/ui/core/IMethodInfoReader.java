package com.karl.debugger.ui.core;

import com.karl.debugger.ui.model.dto.MethodInfo;

import java.lang.reflect.Method;

/**
 * 方法读取器
 *
 * @author karl
 * @date 2018/7/19
 */
public interface IMethodInfoReader {
    /**
     * 反射读取方法返回对应的信息
     * @param method 反射方法对象
     * @return 方法信息
     */
    MethodInfo read(Method method);
}
