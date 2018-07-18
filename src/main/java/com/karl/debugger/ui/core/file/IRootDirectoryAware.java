package com.karl.debugger.ui.core.file;

import java.io.IOException;

/**
 * 底层根目录识别器
 *
 * @author karl
 * @date 2018/7/18
 */
public interface IRootDirectoryAware {
    /**
     * 获取基层路径
     * @return 返回url
     * @throws IOException 获取异常时抛出
     */
    String getRootDir() throws IOException;
}
