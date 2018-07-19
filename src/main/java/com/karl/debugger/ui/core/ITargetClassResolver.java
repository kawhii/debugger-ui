package com.karl.debugger.ui.core;

import com.karl.debugger.ui.model.dto.ClassTargetDTO;
import com.karl.debugger.ui.model.dto.InvokeClassDTO;

/**
 * 根据前端传来的最终执行字符串类名转成java能识别的class
 *
 * @author karl
 * @date 2018/7/19
 */
public interface ITargetClassResolver {
    /**
     * 获取class对象
     * @param invokeClass 字符串转译
     * @return
     * @throws ClassNotFoundException 当class找不到时抛出
     */
    ClassTargetDTO resolve(InvokeClassDTO invokeClass) throws ClassNotFoundException ;
}
