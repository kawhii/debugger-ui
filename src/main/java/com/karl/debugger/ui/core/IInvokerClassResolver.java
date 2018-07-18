package com.karl.debugger.ui.core;

import com.karl.debugger.ui.model.dto.InvokeClassDTO;

/**
 * 执行对象取决器
 *
 * @author karl
 * @date 2018/7/18
 */
public interface IInvokerClassResolver {
    /**
     * 夺回正常的对象
     * @param clazz 执行类
     * @param method 执行方法
     * @param types 方法类型
     * @return 执行class
     */
    InvokeClassDTO resolve(String clazz, String method, String types);

}
