package com.karl.debugger.ui.core.file;

import java.io.IOException;

/**
 * 默认的实现
 *
 * @author karl
 * @date 2018/7/18
 */
public class DefaultRootDirectoryAware implements IRootDirectoryAware {
    @Override
    public String getRootDir() throws IOException {
        return this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
    }
}
