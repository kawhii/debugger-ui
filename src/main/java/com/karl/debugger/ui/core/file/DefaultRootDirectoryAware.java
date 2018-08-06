package com.karl.debugger.ui.core.file;

import org.springframework.boot.ApplicationHome;
import org.springframework.boot.SpringApplication;
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
    private ApplicationHome home = new ApplicationHome(new SpringApplication().getMainApplicationClass());

    @Override
    public String getRootDir() throws IOException {
        String path = home.getDir().getAbsolutePath();
        return path;
    }
}
