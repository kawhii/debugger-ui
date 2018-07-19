package com.karl.debugger.ui.core;

import com.karl.debugger.ui.core.exception.InstanceException;

/**
 * 执行实例获取策略，用于执行方法前进行获取当前实例再进行执行
 *
 * @author karl
 * @date 2018/7/19
 */
public interface IInstanceStrategy {
    Object getInstance(Class<?> clazz, Object ... args) throws InstanceException;
}
