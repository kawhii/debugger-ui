package com.karl.debugger.ui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * mvc设置
 *
 * @author karl
 * @date 2018/7/22
 */
@Configuration
public class DebuggerMVCConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        //路径后缀mapping去掉
        configurer.favorPathExtension(false);
    }
}
