package com.karl.debugger.ui.core.file;

import com.karl.debugger.ui.core.IClassInfoReader;
import com.karl.debugger.ui.core.SimpleClassInfoReader;
import com.karl.debugger.ui.model.dto.ClassInfo;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 类文件渲染器
 *
 * @author karl
 * @date 2018/7/22
 */
public class ClassFileRender extends BaseFileRender<ClassInfo> {
    private IClassInfoReader classInfoReader = new SimpleClassInfoReader();

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
        String clazz = filePath.substring(0, filePath.lastIndexOf("."))
                .replaceAll("/",".");
        try {
            return classInfoReader.read(Class.forName(clazz));
        } catch (ClassNotFoundException e) {
            throw new FileNotFoundException(filePath);
        }
    }
}
