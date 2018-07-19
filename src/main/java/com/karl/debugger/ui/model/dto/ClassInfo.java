package com.karl.debugger.ui.model.dto;

import java.util.List;

/**
 * 类信息 todo 字段需要添加
 *
 * @author karl
 * @date 2018/7/19
 */
public class ClassInfo {
    /**
     * 修饰符的值
     */
    private int modifiersVal;
    /**
     * 修饰符字符串
     * Modifier.toString(modifiersVal)
     */
    private String modifiersStr;
    /**
     * 类名
     */
    private String className;
    /**
     * 包名
     */
    private String packageName;
    /**
     * 类方法
     */
    private List<MethodInfo> methods;
    /**
     * 继承类
     */
    private String superClassName;
    /**
     * 接口类
     */
    private List<String> interfaceClassNames;

    public List<MethodInfo> getMethods() {
        return methods;
    }

    public ClassInfo setMethods(List<MethodInfo> methods) {
        this.methods = methods;
        return this;
    }
}
