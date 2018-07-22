package com.karl.debugger.ui.core.file;

import com.karl.debugger.ui.model.dto.FileContentDTO;

import java.io.IOException;

/**
 * 文件渲染器
 *
 * @author karl
 * @date 2018/7/19
 */
public interface IFileRender<B> {
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

    /**
     * 文件渲染
     * @param filePath
     * @return
     * @throws IOException
     */
    B render(String filePath) throws IOException;
}
