package com.karl.debugger.ui.endpoint;

import com.karl.debugger.ui.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 文件列表
 *
 * @author karl
 * @date 2018/7/18
 */
@RestController
@RequestMapping("/dg/tree")
public class FileEndpoint {
    @Autowired
    private IFileService fileService;
    @GetMapping(path = "/**")
    public Object getFiles(HttpServletRequest request) throws IOException {
        String urlTail = new AntPathMatcher()
                .extractPathWithinPattern("/{dg}/{tree}/**", request.getRequestURI());
        return fileService.list(urlTail);
    }
}
