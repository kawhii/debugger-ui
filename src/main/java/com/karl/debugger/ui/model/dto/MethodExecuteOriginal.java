package com.karl.debugger.ui.model.dto;

import java.util.List;

/**
 * 方法执行原始信息
 *
 * @author karl
 * @date 2018/8/4
 */
public class MethodExecuteOriginal {
    /**
     * 执行类名
     */
    private String className;
    /**
     * 执行方法
     */
    private String methodName;
    /**
     * 执行方法类型
     */
    private List<String> paramsTypes;
    /**
     * 执行参数值（未解析)
     */
    private List<String> paramsValue;

    public String getClassName() {
        return className;
    }

    public MethodExecuteOriginal setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public MethodExecuteOriginal setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public List<String> getParamsTypes() {
        return paramsTypes;
    }

    public MethodExecuteOriginal setParamsTypes(List<String> paramsTypes) {
        this.paramsTypes = paramsTypes;
        return this;
    }

    public List<String> getParamsValue() {
        return paramsValue;
    }

    public MethodExecuteOriginal setParamsValue(List<String> paramsValue) {
        this.paramsValue = paramsValue;
        return this;
    }
}
