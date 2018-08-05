package com.karl.debugger.ui.core.exception;

import com.karl.debugger.ui.model.dto.MethodExecuteInstance;

/**
 * 方法执行错误时抛出
 *
 * @author karl
 * @date 2018/8/5
 */
public class MethodInvokeException extends Exception {
    private MethodExecuteInstance instance;

    public MethodInvokeException(String message, Throwable cause, MethodExecuteInstance instance) {
        super(message, cause);
        this.instance = instance;
    }

    public MethodInvokeException(Throwable cause, MethodExecuteInstance instance) {
        super(cause);
        this.instance = instance;
    }

    public MethodExecuteInstance getInstance() {
        return instance;
    }
}
