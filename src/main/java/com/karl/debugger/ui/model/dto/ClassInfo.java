package com.karl.debugger.ui.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * 类信息 todo 字段需要添加
 *
 * @author karl
 * @date 2018/7/19
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    public int getModifiersVal() {
        return modifiersVal;
    }

    public ClassInfo setModifiersVal(int modifiersVal) {
        this.modifiersVal = modifiersVal;
        return this;
    }

    public String getModifiersStr() {
        return modifiersStr;
    }

    public ClassInfo setModifiersStr(String modifiersStr) {
        this.modifiersStr = modifiersStr;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public ClassInfo setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getPackageName() {
        return packageName;
    }

    public ClassInfo setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public ClassInfo setSuperClassName(String superClassName) {
        this.superClassName = superClassName;
        return this;
    }

    public List<String> getInterfaceClassNames() {
        return interfaceClassNames;
    }

    public ClassInfo setInterfaceClassNames(List<String> interfaceClassNames) {
        this.interfaceClassNames = interfaceClassNames;
        return this;
    }
}
