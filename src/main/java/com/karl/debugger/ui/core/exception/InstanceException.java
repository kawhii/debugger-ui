package com.karl.debugger.ui.core.exception;

/**
 * 实例获取异常时进行抛出
 *
 * @author karl
 * @date 2018/7/19
 */
public class InstanceException extends Exception {
    public InstanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InstanceException(Throwable cause) {
        super(cause);
    }
}
