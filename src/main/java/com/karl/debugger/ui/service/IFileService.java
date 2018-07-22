package com.karl.debugger.ui.service;

import com.karl.debugger.ui.model.dto.FileContentDTO;
import com.karl.debugger.ui.model.dto.FileDTO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author karl
 * @date 2018/7/18
 */
public interface IFileService {
    /**
     * 文件列表
     *
     * @param basePath 文件基础路径
     * @return 文件列表
     * @throws IOException
     */
    List<FileDTO> list(String basePath) throws IOException;

    /**
     * 查看文件内容
     * @param filePath 文件路径
     * @return 根据不同文件返回不同内容
     * @throws IOException
     */
    FileContentDTO<?> fileDetail(String filePath) throws IOException;
}
