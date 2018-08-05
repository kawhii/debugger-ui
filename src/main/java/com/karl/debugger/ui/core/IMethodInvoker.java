package com.karl.debugger.ui.core;

import com.karl.debugger.ui.core.exception.MethodInvokeException;
import com.karl.debugger.ui.model.dto.MethodExecuteInstance;

/**
 * 方法执行者
 *
 * @author karl
 * @date 2018/8/5
 */
public interface IMethodInvoker {
    /**
     * 执行方法
     * @param instance 执行方法实例
     * @return
     * @throws MethodInvokeException 方法执行有误时抛出
     */
    Object invoke(MethodExecuteInstance instance) throws MethodInvokeException;
}
