package com.karl.debugger.ui.service;

import com.karl.debugger.ui.model.dto.FileDTO;

import java.io.IOException;
import java.util.List;

/**
 * @author karl
 * @date 2018/7/18
 */
public interface IFileService {
    /**
     * 文件列表
     * @param basePath 文件基础路径
     * @return 文件列表
     * @throws IOException
     */
    List<FileDTO> list(String basePath) throws IOException;
}
