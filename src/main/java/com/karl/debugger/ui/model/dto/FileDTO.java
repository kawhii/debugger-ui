package com.karl.debugger.ui.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 文件内容
 *
 * @author karl
 * @date 2018/7/18
 */
public class FileDTO {
    /**
     * 文件名称
     */
    private String name;
    /**
     * 是否目录
     */
    @JsonProperty("isDir")
    private Boolean dir;

    /**
     * 文件大小，dir=false才有
     */
    private Long size;
    /**
     * 上次修改时间
     */
    private Long lastModified;

    /**
     * 路径
     */
    private String path;

    public String getName() {
        return name;
    }

    public FileDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getDir() {
        return dir;
    }

    public FileDTO setDir(Boolean dir) {
        this.dir = dir;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public FileDTO setSize(Long size) {
        this.size = size;
        return this;
    }

    public Long getLastModified() {
        return lastModified;
    }

    public FileDTO setLastModified(Long lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    public String getPath() {
        return path;
    }

    public FileDTO setPath(String path) {
        this.path = path;
        return this;
    }
}
