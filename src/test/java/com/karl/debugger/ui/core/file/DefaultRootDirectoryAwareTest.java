package com.karl.debugger.ui.core.file;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author karl
 * @date 2018/7/18
 */
public class DefaultRootDirectoryAwareTest {
    private DefaultRootDirectoryAware rootDirectoryAware = new DefaultRootDirectoryAware();

    @Test
    public void getRootDir() throws IOException {
        Object dir = rootDirectoryAware.getRootDir();
        assertNotNull(dir);
    }
}