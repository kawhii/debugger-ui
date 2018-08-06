package com.karl.debugger.ui.core.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

/**
 * 直接输出的文件渲染器
 *
 * @author karl
 * @date 2018/7/22
 */
public class SystemBlobFileRender extends BaseFileRender<String> {
    /**
     * 这些后缀能够被处理
     */
    @Autowired
    @Qualifier("fileProcessSuffix")
    private Set<String> fileProcessSuffix;

    @Override
    public String name() {
        return "blob";
    }

    @Override
    public boolean support(String suffix) {
        return fileProcessSuffix.contains(suffix);
    }

    @Override
    public String render(String filePath) throws IOException {
        String basePath = rootDirectoryAware.getRootDir();
        File file = new File(basePath, filePath);
        String str = FileCopyUtils.copyToString(new FileReader(file));
        return Base64Utils.encodeToString(str.getBytes("UTF-8"));
    }
}
