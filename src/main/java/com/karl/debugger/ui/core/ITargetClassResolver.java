package com.karl.debugger.ui.core;

import com.karl.debugger.ui.model.dto.ClassTargetDTO;
import com.karl.debugger.ui.model.dto.InvokeClassDTO;

/**
 * @author karl
 * @date 2018/7/19
 */
public interface ITargetClassResolver {
    ClassTargetDTO resolve(InvokeClassDTO invokeClass) throws ClassNotFoundException ;
}
