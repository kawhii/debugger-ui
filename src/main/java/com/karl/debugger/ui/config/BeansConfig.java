package com.karl.debugger.ui.config;

import com.karl.debugger.ui.core.SpringApplicationContextInstanceStrategy;
import com.karl.debugger.ui.core.file.BlobFileRender;
import com.karl.debugger.ui.core.file.ClassFileRender;
import com.karl.debugger.ui.core.file.IFileRender;
import com.karl.debugger.ui.service.IFileService;
import com.karl.debugger.ui.service.impl.JarFileServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
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
    protected IFileRender blobFileRender() {
        return new BlobFileRender();
    }

    @Bean
    protected IFileService fileService() throws IOException {
        String path = "/Users/karl/Documents/Work/Person/Code/debugger-ui/target/debugger-ui.jar";

        return new JarFileServiceImpl(new JarFile(path));
    }
}
