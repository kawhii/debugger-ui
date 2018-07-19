package com.karl.debugger.ui.model.dto;

import java.util.List;

/**
 * 根据类反应出的真实执行信息
 *
 * @author karl
 * @date 2018/7/19
 */
public class ClassTargetDTO {
    /**
     * 执行类
     */
    private Class<?> clazz;
    /**
     * 执行方法名
     */
    private String methodName;
    /**
     * 方法参数类型
     */
    private List<Class<?>> types;

    public Class<?> getClazz() {
        return clazz;
    }

    public ClassTargetDTO setClazz(Class<?> clazz) {
        this.clazz = clazz;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public ClassTargetDTO setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public List<Class<?>> getTypes() {
        return types;
    }

    public ClassTargetDTO setTypes(List<Class<?>> types) {
        this.types = types;
        return this;
    }
}
