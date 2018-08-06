package com.karl.debugger.ui.config;

import com.karl.debugger.ui.core.SpringApplicationContextInstanceStrategy;
import com.karl.debugger.ui.core.file.JarBlobFileRender;
import com.karl.debugger.ui.core.file.SystemBlobFileRender;
import com.karl.debugger.ui.core.file.ClassFileRender;
import com.karl.debugger.ui.core.file.IFileRender;
import com.karl.debugger.ui.service.IFileService;
import com.karl.debugger.ui.service.impl.JarFileServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarFile;

/**
 * 实例配置
 *
 * @author karl
 * @date 2018/7/19
 */
@Configuration
public class BeansConfig {
    /**
     * spring的bean获取执行策略
     * @return
     */
    @Bean("springApplicationContextInstanceStrategy")
    protected SpringApplicationContextInstanceStrategy instanceStrategy() {
        return new SpringApplicationContextInstanceStrategy();
    }

    /**
     * 文件后缀允许处理
     * @return
     */
    @Bean("fileProcessSuffix")
    protected Set<String> fileProcessSuffix() {
        Set<String> fileProcess = new HashSet<>();
        fileProcess.add("yml");
        fileProcess.add("yaml");
        fileProcess.add("properties");
        fileProcess.add("js");
        fileProcess.add("css");
        fileProcess.add("ini");
        fileProcess.add("html");
        return fileProcess;
    }

    /**
     * 类文件渲染器
     * @return
     */
    @Bean
    protected IFileRender classFileRender() {
        return new ClassFileRender();
    }

    /**
     * 可直接输出文件渲染器
     * @return
     */
    @Bean
    protected IFileRender blobFileRender() throws IOException {
//        return new SystemBlobFileRender();
        String path = "/Users/karl/Documents/Work/Person/Code/debugger-ui/target/debugger-ui.jar";

        return new JarBlobFileRender(new JarFile(path));
    }

    @Bean
    protected IFileService fileService() throws IOException {
        String path = "/Users/karl/Documents/Work/Person/Code/debugger-ui/target/debugger-ui.jar";

        return new JarFileServiceImpl(new JarFile(path));
    }
}
