package com.karl.debugger.ui.core;

import com.karl.debugger.ui.core.exception.InstanceException;

/**
 * 执行实例获取策略，用于执行方法前进行获取当前实例再进行执行
 *
 * @author karl
 * @date 2018/7/19
 */
public interface IInstanceStrategy {
    /**
     * 获取执行实例
     * @param clazz 类
     * @param args 参数或类名等
     * @return
     * @throws InstanceException 发生任何异常时抛出
     */
    Object getInstance(Class<?> clazz, Object ... args) throws InstanceException;
}
