package com.karl.debugger.ui.core.file;

import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 默认的实现
 *
 * @author karl
 * @date 2018/7/18
 */
@Component
public class DefaultRootDirectoryAware implements IRootDirectoryAware {
    @Override
    public String getRootDir() throws IOException {
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
        return path.substring(path.indexOf(":") + 1);
    }
}
