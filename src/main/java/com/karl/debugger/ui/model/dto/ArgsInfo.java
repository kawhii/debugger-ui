package com.karl.debugger.ui.model.dto;

import java.util.List;

/**
 * 参数信息
 *
 * @author karl
 * @date 2018/7/19
 */
public class ArgsInfo {
    /**
     * 参数名称
     */
    private String argName;
    /**
     * 注解类型
     */
    private List<String> annotations;
    /**
     * 参数类型
     */
    private String type;

    public String getArgName() {
        return argName;
    }

    public ArgsInfo setArgName(String argName) {
        this.argName = argName;
        return this;
    }


    public List<String> getAnnotations() {
        return annotations;
    }

    public ArgsInfo setAnnotations(List<String> annotations) {
        this.annotations = annotations;
        return this;
    }

    public String getType() {
        return type;
    }

    public ArgsInfo setType(String type) {
        this.type = type;
        return this;
    }
}
