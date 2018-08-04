package com.karl.debugger.ui.core;

import com.karl.debugger.ui.core.exception.InstanceException;
import com.karl.debugger.ui.model.dto.MethodExecuteInstance;
import com.karl.debugger.ui.model.dto.MethodExecuteOriginal;

/**
 * 方法执行实例构造器
 *
 * @author karl
 * @date 2018/8/4
 */
public interface IMethodExecuteInstanceBuilder {
    /**
     * 根据前端原始对象构建执行实例
     * @param original 原始对象
     * @return
     * @throws ClassNotFoundException
     */
    MethodExecuteInstance build(MethodExecuteOriginal original) throws ClassNotFoundException, InstanceException, NoSuchMethodException;
}
