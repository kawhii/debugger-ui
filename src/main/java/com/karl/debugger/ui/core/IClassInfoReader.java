package com.karl.debugger.ui.core;

import com.karl.debugger.ui.model.dto.ClassInfo;

/**
 * 类信息读取器
 *
 * @author karl
 * @date 2018/7/19
 */
public interface IClassInfoReader {
    /**
     * 读取类信息内容
     * @param clazz 具体class类
     * @return 类信息
     */
    ClassInfo read(Class<?> clazz);
}
