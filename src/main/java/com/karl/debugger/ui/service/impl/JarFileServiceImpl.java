package com.karl.debugger.ui.service.impl;

import com.karl.debugger.ui.core.file.IFileRender;
import com.karl.debugger.ui.model.dto.FileContentDTO;
import com.karl.debugger.ui.model.dto.FileDTO;
import com.karl.debugger.ui.service.IFileService;
import com.karl.debugger.ui.utils.JarEntryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.nio.file.NotLinkException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;

/**
 * jar包文件读取服务
 *
 * @author karl
 * @date 2018/8/6
 */
public class JarFileServiceImpl implements IFileService {

    private JarFile jarFile;

    @Autowired
    private List<IFileRender> renders;

    public JarFileServiceImpl(JarFile jarFile) {
        this.jarFile = jarFile;
    }

    @Override
    public List<FileDTO> list(String basePath) throws IOException {
        basePath = basePath == null ? "" : basePath;
        final List<FileDTO> list = new ArrayList<>();
        Stream<JarEntry> stream = jarFile.stream();

        stream
                //过滤是在classes下的文件
                .filter(jarEntry -> jarEntry.getName().startsWith(JarEntryUtils.PREFIX)
                        && jarEntry.getName().length() > JarEntryUtils.PREFIX.length())
                //若为根目录则采用root集合过滤
                .filter(basePath.length() > 0 ? new PathFileFilter(basePath)::filter : this::rootFileFilter)
                //遍历获取文件名
                .forEach(jarEntry -> {
                    FileDTO fileDTO = new FileDTO()
                            .setDir(jarEntry.isDirectory())
                            .setPath(JarEntryUtils.getPath(jarEntry))
                            .setSize(jarEntry.getSize())
                            .setLastModified(jarEntry.getLastModifiedTime().to(TimeUnit.MILLISECONDS))
                            .setName(JarEntryUtils.getName(jarEntry));
                    list.add(fileDTO);
                });
        return list;
    }

    /**
     * 根目录文件
     *
     * @param jarEntry
     * @return
     */
    private boolean rootFileFilter(JarEntry jarEntry) {
        String path = JarEntryUtils.getPath(jarEntry);
        return !path.contains("/");
    }

    private class PathFileFilter {
        private String basePath;

        public PathFileFilter(String basePath) {
            this.basePath = basePath;
        }

        protected boolean filter(JarEntry jarEntry) {
            String path = JarEntryUtils.getPath(jarEntry);
            return path.startsWith(basePath) && path.length() > basePath.length() && path.substring(basePath.length()).split("/").length == 2;
        }
    }


    @Override
    public FileContentDTO<?> fileDetail(String filePath) throws IOException {
        //后缀
        String suffix = UriUtils.extractFileExtension(filePath);
        FileContentDTO fileContentDTO = new FileContentDTO();
        fileContentDTO.setSuffix(suffix);

        //找到合适的渲染器进行渲染
        if (SystemFileServiceImpl.renderFile(filePath, suffix, fileContentDTO, renders)) {
            return fileContentDTO;
        }

        throw new NotLinkException(filePath);
    }
}
