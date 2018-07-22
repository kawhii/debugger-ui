package com.karl.debugger.ui.core.file;

import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 直接输出的文件渲染器
 *
 * @author karl
 * @date 2018/7/22
 */
public class BlobFileRender extends BaseFileRender<String> {
    @Override
    public String name() {
        return "blob";
    }

    @Override
    public boolean support(String suffix) {
        return "properties".equals(suffix) || "yml".equals(suffix);
    }

    @Override
    public String render(String filePath) throws IOException {
        String basePath = rootDirectoryAware.getRootDir();
        File file = new File(basePath, filePath);
        String str = FileCopyUtils.copyToString(new FileReader(file));
        return Base64Utils.encodeToString(str.getBytes("UTF-8"));
    }
}
