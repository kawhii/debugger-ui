package com.karl.debugger.ui.core.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

/**
 * 基层文件渲染，主要是负责一些公共的方法处理
 *
 * @author karl
 * @date 2018/7/22
 */
public abstract class BaseFileRender<B> implements IFileRender<B> {
    protected IRootDirectoryAware rootDirectoryAware = new DefaultRootDirectoryAware();

    /**
     * 检查文件是否存在
     * @param filePath 检查文件
     * @throws IOException
     */
    protected void checkExists(String filePath) throws IOException {
        String basePath = rootDirectoryAware.getRootDir();
        File file = new File(basePath, filePath);
        if(!file.exists()) {
            throw new FileNotFoundException(filePath);
        }

        if(!file.isFile()) {
            throw new AccessDeniedException(filePath);
        }
    }
}
