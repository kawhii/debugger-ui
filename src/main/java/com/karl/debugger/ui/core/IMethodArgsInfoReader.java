package com.karl.debugger.ui.core;

import com.karl.debugger.ui.model.dto.ArgsInfo;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;

/**
 * 方法参数读取器
 *
 * @author karl
 * @date 2018/7/19
 */
public interface IMethodArgsInfoReader {
    /**
     * 读取参数信息
     *
     * @param parameter 参数信息
     * @return
     */
    ArgsInfo read(Parameter parameter);
}
