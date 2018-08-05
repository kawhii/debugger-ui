package com.karl.debugger.ui.endpoint;

import com.karl.debugger.ui.model.dto.EndpointResponse;
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
@RequestMapping("/dg")
public class FileEndpoint extends BaseEndpoint {
    @Autowired
    private IFileService fileService;

    @GetMapping(path = "/tree/**")
    public EndpointResponse<?> getFiles(HttpServletRequest request) throws IOException {
        String urlTail = new AntPathMatcher()
                .extractPathWithinPattern("/{dg}/{tree}/**", request.getRequestURI());
        Object res = fileService.list(urlTail);
        return EndpointResponse.success(res);
    }

    @GetMapping(path = "/blob/**")
    public Object fileContent(HttpServletRequest request) throws IOException {
        String urlTail = new AntPathMatcher()
                .extractPathWithinPattern("/{dg}/{blob}/**", request.getRequestURI());
        return EndpointResponse.success(fileService.fileDetail(urlTail));
    }
}
