package com.karl.debugger.ui.service.impl;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.jar.JarFile;

import static org.junit.Assert.*;

/**
 * @author karl
 * @date 2018/8/6
 */
public class JarFileServiceImplTest {
    private JarFileServiceImpl jarFileService;

    @Before
    public void setUp() throws Exception {
        String path = "/Users/karl/Documents/Work/Person/Code/debugger-ui/target/debugger-ui.jar";

//        jarFileService = new JarFileServiceImpl(new JarFile(path));
    }

    @Test
    public void list() throws IOException {
//        jarFileService.list("public");
    }

    @Test
    public void fileDetail() {
    }
}