package com.karl.debugger.ui.model.dto;

import java.util.List;

/**
 * 方法信息
 *
 * @author karl
 * @date 2018/7/19
 */
public class MethodInfo {
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 修饰符的值
     */
    private int modifiersVal;
    //
    /**
     * 修饰符字符串
     * Modifier.toString(modifiersVal)
     */
    private String modifiersStr;
    /**
     * 抛出类型
     */
    private List<String> throwsTypes;
    /**
     * 返回类型
     */
    private String returnType;
    /**
     * 参数信息
     */
    private List<ArgsInfo> args;
    /**
     * 注解
     */
    private List<String> annotations;

    public String getMethodName() {
        return methodName;
    }

    public MethodInfo setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public int getModifiersVal() {
        return modifiersVal;
    }

    public MethodInfo setModifiersVal(int modifiersVal) {
        this.modifiersVal = modifiersVal;
        return this;
    }

    public String getModifiersStr() {
        return modifiersStr;
    }

    public MethodInfo setModifiersStr(String modifiersStr) {
        this.modifiersStr = modifiersStr;
        return this;
    }

    public List<String> getThrowsTypes() {
        return throwsTypes;
    }

    public MethodInfo setThrowsTypes(List<String> throwsTypes) {
        this.throwsTypes = throwsTypes;
        return this;
    }

    public String getReturnType() {
        return returnType;
    }

    public MethodInfo setReturnType(String returnType) {
        this.returnType = returnType;
        return this;
    }

    public List<ArgsInfo> getArgs() {
        return args;
    }

    public MethodInfo setArgs(List<ArgsInfo> args) {
        this.args = args;
        return this;
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public MethodInfo setAnnotations(List<String> annotations) {
        this.annotations = annotations;
        return this;
    }
}
