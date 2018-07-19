package com.karl.debugger.ui.core;

/**
 * 文件渲染器
 *
 * @author karl
 * @date 2018/7/19
 */
public interface IFileRender {
    /**
     * 渲染器名称
     * @return 唯一名称
     */
    String name();

    /**
     * 是否支持该文件后缀渲染
     * @param suffix 后缀
     * @return true可以渲染否则不可以
     */
    boolean support(String suffix);

    //todo 定义渲染接口

}
