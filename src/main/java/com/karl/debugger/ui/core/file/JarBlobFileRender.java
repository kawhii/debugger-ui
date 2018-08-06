package com.karl.debugger.ui.core.file;

import com.karl.debugger.ui.utils.JarEntryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Base64Utils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 直接输出的文件渲染器
 *
 * @author karl
 * @date 2018/7/22
 */
public class JarBlobFileRender extends BaseFileRender<String> {
    /**
     * 这些后缀能够被处理
     */
    @Autowired
    @Qualifier("fileProcessSuffix")
    private Set<String> fileProcessSuffix;

    /**
     * jar包
     */
    private JarFile jarFile;

    public JarBlobFileRender(JarFile jarFile) {
        this.jarFile = jarFile;
    }

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
        JarEntry file = jarFile.stream()
                //过滤是在classes下的文件
                .filter(jarEntry -> jarEntry.getName().startsWith(JarEntryUtils.PREFIX)
                        && jarEntry.getName().length() > JarEntryUtils.PREFIX.length())
                .filter(jarEntry -> JarEntryUtils.getPath(jarEntry).equals(filePath)).findFirst().get();
        //把文件读取流输出
        InputStream inputStream = jarFile.getInputStream(file);
        byte[] res = StreamUtils.copyToByteArray(inputStream);
        return Base64Utils.encodeToString(res);
    }
}
