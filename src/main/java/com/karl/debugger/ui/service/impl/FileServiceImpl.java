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

        //若是目录递归文件
        if(file.isDirectory()) {
            File[] fs = file.listFiles();
            List<FileDTO> files = new ArrayList<>();
            for(File f : fs) {
                FileDetail fd = resolveFile(new FileDetail(f, f.getName()));
                File clearFile = fd.file;
                String filePath = clearFile.getPath();
                files.add(new FileDTO()
                        .setDir(clearFile.isDirectory())
                        .setLastModified(clearFile.lastModified())
                        .setName(fd.clearName)
                        .setPath(filePath)
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
    private FileDetail resolveFile(FileDetail f) {
        File next;
        if(f.file.isDirectory() && f.file.list().length == 1 && (next = f.file.listFiles()[0]).isDirectory()) {
            f.clearName = f.clearName + "/" + next.getName();
            f.file = next;
            return resolveFile(f);
        }
        return f;
    }

    private class FileDetail {
        private File file;
        private String clearName;

        public FileDetail(File file, String clearName) {
            this.file = file;
            this.clearName = clearName;
        }
    }
}
