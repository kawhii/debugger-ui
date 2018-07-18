package com.karl.debugger.ui.model.dto;

import java.util.List;

/**
 * 用于目标执行的基层类数据
 *
 * @author karl
 * @date 2018/7/18
 */
public class InvokeClassDTO {
    /**
     * 执行class名称
     */
    private String className;
    /**
     * 执行方法名
     */
    private String methodName;
    /**
     * 执行类型
     */
    private List<String> types;

    public String getClassName() {
        return className;
    }

    public InvokeClassDTO setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public InvokeClassDTO setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public List<String> getTypes() {
        return types;
    }

    public InvokeClassDTO setTypes(List<String> types) {
        this.types = types;
        return this;
    }


    //todo 可考虑缓存，当三者一致时则意味着是一样的，无需重新反射
}
