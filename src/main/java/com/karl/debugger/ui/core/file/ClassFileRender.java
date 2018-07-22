package com.karl.debugger.ui.core.file;

import com.karl.debugger.ui.core.IClassInfoReader;
import com.karl.debugger.ui.core.SimpleClassInfoReader;
import com.karl.debugger.ui.model.dto.ClassInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

/**
 * 类文件渲染器
 *
 * @author karl
 * @date 2018/7/22
 */
public class ClassFileRender implements IFileRender<ClassInfo> {
    private IClassInfoReader classInfoReader = new SimpleClassInfoReader();
    private IRootDirectoryAware rootDirectoryAware = new DefaultRootDirectoryAware();

    @Override
    public String name() {
        return "java";
    }

    @Override
    public boolean support(String suffix) {
        return "class".equals(suffix) || "java".equals(suffix);
    }

    @Override
    public ClassInfo render(String filePath) throws IOException {
        String basePath = rootDirectoryAware.getRootDir();
        File file = new File(basePath, filePath);
        if(!file.exists()) {
            throw new FileNotFoundException(filePath);
        }

        if(!file.isFile()) {
            throw new AccessDeniedException(filePath);
        }
        String clazz = filePath.substring(0, filePath.lastIndexOf("."))
                .replaceAll("/",".");
        try {
            return classInfoReader.read(Class.forName(clazz));
        } catch (ClassNotFoundException e) {
            throw new FileNotFoundException(filePath);
        }
    }
}
