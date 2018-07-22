package com.karl.debugger.ui.config;

import com.karl.debugger.ui.core.IInstanceStrategy;
import com.karl.debugger.ui.core.SpringApplicationContextInstanceStrategy;
import com.karl.debugger.ui.core.file.ClassFileRender;
import com.karl.debugger.ui.core.file.IFileRender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    protected IInstanceStrategy instanceStrategy() {
        return new SpringApplicationContextInstanceStrategy();
    }

    @Bean
    protected IFileRender classFileRender() {
        return new ClassFileRender();
    }
}
