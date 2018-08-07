package com.karl.debugger.ui.config;

import com.karl.debugger.ui.core.strategy.DelegateInstanceStrategy;
import com.karl.debugger.ui.core.IInstanceStrategy;
import com.karl.debugger.ui.core.strategy.SpringApplicationContextInstanceStrategy;
import com.karl.debugger.ui.core.condition.JarFileCondition;
import com.karl.debugger.ui.core.condition.SystemPathCondition;
import com.karl.debugger.ui.core.file.JarBlobFileRender;
import com.karl.debugger.ui.core.file.SystemBlobFileRender;
import com.karl.debugger.ui.core.file.ClassFileRender;
import com.karl.debugger.ui.core.file.IFileRender;
import com.karl.debugger.ui.service.IFileService;
import com.karl.debugger.ui.service.impl.JarFileServiceImpl;
import com.karl.debugger.ui.service.impl.SystemFileServiceImpl;
import org.springframework.boot.ApplicationHome;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarFile;

/**
 * 实例配置
 *
 * @author karl
 * @date 2018/7/19
 */
@Configuration
@ComponentScan(basePackages = "com.karl.debugger.ui")
public class BeansConfig {
    /**
     * spring的bean获取执行策略
     * @return
     */
    @Bean("springApplicationContextInstanceStrategy")
    protected SpringApplicationContextInstanceStrategy springApplicationContextInstanceStrategy() {
        return new SpringApplicationContextInstanceStrategy();
    }

    @Bean("delegateInstanceStrategy")
    protected DelegateInstanceStrategy delegateInstanceStrategy(List<IInstanceStrategy> strategies) {
        return new DelegateInstanceStrategy(strategies);
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
        fileProcess.add("xml");
        fileProcess.add("xsd");
        fileProcess.add("txt");
        fileProcess.add("sql");
        fileProcess.add("ftl");
        fileProcess.add("jsp");
        fileProcess.add("vm");
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
    @Conditional(JarFileCondition.class)
    protected IFileRender jarBlobFileRender(JarFile jarFile) {
        return new JarBlobFileRender(jarFile);
    }

    /**
     * 系统文件读取
     * @return
     */
    @Bean
    @Conditional(SystemPathCondition.class)
    protected IFileRender systemBlobFileRender() {
        return new SystemBlobFileRender();
    }

    /**
     * jar包方式读取
     * @param jarFile
     * @return
     */
    @Bean
    @Conditional(JarFileCondition.class)
    protected IFileService jarFileService(JarFile jarFile) {
        return new JarFileServiceImpl(jarFile);
    }

    /**
     * 系统路径方式读取
     * @return
     */
    @Bean
    @Conditional(SystemPathCondition.class)
    protected IFileService systemFileService() {
        return new SystemFileServiceImpl();
    }

    @Bean("springBootJarFile")
    @Conditional(JarFileCondition.class)
    protected JarFile jarFile() {
        ApplicationHome home = new ApplicationHome(new SpringApplication().getMainApplicationClass());
        try {
            return new JarFile(home.getSource().getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
