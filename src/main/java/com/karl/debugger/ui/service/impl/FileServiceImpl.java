package com.karl.debugger.ui.service.impl;

import com.karl.debugger.ui.core.file.IRootDirectoryAware;
import com.karl.debugger.ui.model.dto.FileDTO;
import com.karl.debugger.ui.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 默认实现文件服务
 *
 * @author karl
 * @date 2018/7/18
 */
@Service
public class FileServiceImpl implements IFileService {
    @Autowired
    private IRootDirectoryAware directoryAware;

    @Autowired
    private ApplicationContext context;

    @Override
    public List<FileDTO> list(String basePath) throws IOException {
        String baseDir = directoryAware.getRootDir();
        String path = baseDir + basePath;
        File file = context.getResource(path).getFile();
        if(file.isDirectory()) {
            File[] fs = file.listFiles();
            List<FileDTO> files = new ArrayList<>();
            for(File f : fs) {
                File clearFile = resolveFile(f);
                files.add(new FileDTO()
                        .setDir(clearFile.isDirectory())
                        .setLastModified(clearFile.lastModified())
                        .setName(clearFile.getName())
                        .setPath(clearFile.getPath())
                        .setSize(clearFile.length()));
            }
            return files;
        }
        return null;
    }

    /**
     * 确认文件，若当前文件为目录，并且下一级还只有一个文件并且为目录时递归，否则直接返回
     * @param f
     * @return
     */
    private File resolveFile(File f) {
        File next;
        if(f.isDirectory() && f.list().length == 1 && (next = f.listFiles()[0]).isDirectory()) {
            return resolveFile(next);
        }
        return f;
    }
}
