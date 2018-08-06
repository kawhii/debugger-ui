package com.karl.debugger.ui.service.impl;

import com.karl.debugger.ui.core.file.IFileRender;
import com.karl.debugger.ui.core.file.IRootDirectoryAware;
import com.karl.debugger.ui.model.dto.FileContentDTO;
import com.karl.debugger.ui.model.dto.FileDTO;
import com.karl.debugger.ui.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.NotLinkException;
import java.util.ArrayList;
import java.util.List;

/**
 * 默认实现文件服务
 *
 * @author karl
 * @date 2018/7/18
 */
//@Service
public class SystemFileServiceImpl implements IFileService {
    @Autowired
    private IRootDirectoryAware directoryAware;
    @Autowired
    private List<IFileRender> renders;

    @Override
    public List<FileDTO> list(String basePath) throws IOException {
        String baseDir = directoryAware.getRootDir();
        File file = new File(baseDir, basePath);

        //若是目录递归文件
        if (file.isDirectory()) {
            File[] fs = file.listFiles();
            List<FileDTO> files = new ArrayList<>();
            for (File f : fs) {
                FileDetail fd = resolveFile(new FileDetail(f, f.getName()));
                File clearFile = fd.file;
//                String filePath = clearFile.getPath();
                String filePath = StringUtils.isEmpty(basePath) ? f.getName() : basePath + "/" + f.getName();
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

    @Override
    public FileContentDTO fileDetail(String filePath) throws IOException {
        //后缀
        String suffix = UriUtils.extractFileExtension(filePath);
        String baseDir = directoryAware.getRootDir();
        File file = new File(baseDir, filePath);

        //文件不存在
        if(!file.exists()) {
            throw new FileNotFoundException(filePath);
        }

        //非文件不允许读
        if(!file.isFile()) {
            throw new AccessDeniedException(filePath);
        }
        FileContentDTO fileContentDTO = new FileContentDTO();
        fileContentDTO.setSuffix(suffix);

        //找到合适的渲染器进行渲染
        if (renderFile(filePath, suffix, fileContentDTO, renders)) {
            return fileContentDTO;
        }

        throw new NotLinkException(filePath);
    }

    /**
     * 抽离渲染
     * @param filePath
     * @param suffix
     * @param fileContentDTO
     * @param renders
     * @return
     * @throws IOException
     */
    static boolean renderFile(String filePath, String suffix, FileContentDTO fileContentDTO, List<IFileRender> renders) throws IOException {
        for (IFileRender fileRender : renders) {
            if (fileRender.support(suffix)) {
                fileContentDTO.setType(fileRender.name());
                fileContentDTO.setBody(fileRender.render(filePath));
                return true;
            }
        }
        return false;
    }

    /**
     * 确认文件，若当前文件为目录，并且下一级还只有一个文件并且为目录时递归，否则直接返回
     *
     * @param f
     * @return
     */
    private FileDetail resolveFile(FileDetail f) {
        File next;
        if (f.file.isDirectory() && f.file.list().length == 1 && (next = f.file.listFiles()[0]).isDirectory()) {
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
