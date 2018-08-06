package com.karl.debugger.ui.service.impl;

import com.karl.debugger.ui.core.file.IFileRender;
import com.karl.debugger.ui.model.dto.FileContentDTO;
import com.karl.debugger.ui.model.dto.FileDTO;
import com.karl.debugger.ui.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
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

    /**
     * 前缀
     */
    private static final String PREFIX = "BOOT-INF/classes/";

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
                .filter(jarEntry -> jarEntry.getName().startsWith(PREFIX)
                        && jarEntry.getName().length() > PREFIX.length())
                //若为根目录则采用root集合过滤
                .filter(basePath.length() > 0 ? new PathFileFilter(basePath)::filter : this::rootFileFilter)
                //遍历获取文件名
                .forEach(jarEntry -> {
                    FileDTO fileDTO = new FileDTO()
                            .setDir(jarEntry.isDirectory())
                            .setPath(getPath(jarEntry))
                            .setSize(jarEntry.getSize())
                            .setLastModified(jarEntry.getLastModifiedTime().to(TimeUnit.MILLISECONDS))
                            .setName(getName(jarEntry));
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
        String path = getPath(jarEntry);
        return !path.contains("/");
    }

    private class PathFileFilter {
        private String basePath;

        public PathFileFilter(String basePath) {
            this.basePath = basePath;
        }

        protected boolean filter(JarEntry jarEntry) {
            String path = getPath(jarEntry);
            return path.startsWith(basePath) && path.length() > basePath.length() && path.substring(basePath.length()).split("/").length == 2;
        }
    }


    @Override
    public FileContentDTO<?> fileDetail(String filePath) throws IOException {
        JarEntry file = jarFile.stream()
                //过滤是在classes下的文件
                .filter(jarEntry -> jarEntry.getName().startsWith(PREFIX)
                        && jarEntry.getName().length() > PREFIX.length())
                .filter(jarEntry -> getPath(jarEntry).equals(filePath)).findFirst().get();
        if (file != null) {
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
        return null;
    }



    /**
     * 获取文件名称
     *
     * @param jarEntry
     * @return
     */
    private String getName(JarEntry jarEntry) {
        //截止名称开始索引，结束索引，名称长度
        int startIndex, endIndex, nameLength = jarEntry.getName().length();
        String endWith = "/";
        //若最后以目录结尾则切割/
        if (jarEntry.isDirectory() || jarEntry.getName().endsWith(endWith)) {
            endIndex = nameLength - 1;
            String name = jarEntry.getName().substring(0, endIndex);
            startIndex = name.lastIndexOf("/");
        } else {
            //正常切割文件名
            startIndex = jarEntry.getName().lastIndexOf("/");
            endIndex = nameLength;
        }
        return jarEntry.getName().substring(startIndex + 1, endIndex);
    }

    /**
     * 获取文件路径
     *
     * @param jarEntry
     * @return
     */
    private String getPath(JarEntry jarEntry) {
        if (jarEntry.isDirectory()) {
            return jarEntry.getName().substring(PREFIX.length(), jarEntry.getName().length() - 1);
        }
        return jarEntry.getName().substring(PREFIX.length());
    }
}
