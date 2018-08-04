package com.karl.debugger.ui.model.dto;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 方法执行实例
 *
 * @author karl
 * @date 2018/8/4
 */
public class MethodExecuteInstance {
    /**
     * 执行实例
     */
    private Object instance;
    /**
     * 执行方法
     */
    private Method method;
    /**
     * 方法参数
     */
    private List<?> args;


    public Object getInstance() {
        return instance;
    }

    public MethodExecuteInstance setInstance(Object instance) {
        this.instance = instance;
        return this;
    }

    public Method getMethod() {
        return method;
    }

    public MethodExecuteInstance setMethod(Method method) {
        this.method = method;
        return this;
    }

    public List<?> getArgs() {
        return args;
    }

    public MethodExecuteInstance setArgs(List<?> args) {
        this.args = args;
        return this;
    }
}
