package com.karl.debugger.ui.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 文件内容
 *
 * @author karl
 * @date 2018/7/22
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileContentDTO<B> {
    /**
     * 文件类型
     */
    private String type;
    /**
     * 文件后缀
     */
    private String suffix;
    /**
     * 文件内容
     */
    private B body;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public B getBody() {
        return body;
    }

    public void setBody(B body) {
        this.body = body;
    }
}
