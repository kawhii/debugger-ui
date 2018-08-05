package com.karl.debugger.ui.core.exception;

import com.karl.debugger.ui.model.dto.MethodExecuteOriginal;

/**
 * 方法构建实例时出现异常时抛出
 *
 * @author karl
 * @date 2018/8/5
 */
public class MethodInstanceBuilderException extends Exception {
    private MethodExecuteOriginal original;

    public MethodExecuteOriginal getOriginal() {
        return original;
    }

    public MethodInstanceBuilderException(String message, Throwable cause, MethodExecuteOriginal original) {
        super(message, cause);
        this.original = original;
    }

    public MethodInstanceBuilderException(Throwable cause, MethodExecuteOriginal original) {
        super(cause);
        this.original = original;
    }
}
