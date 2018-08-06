package com.karl.debugger.ui.annotation;

import com.karl.debugger.ui.config.BeansConfig;
import com.karl.debugger.ui.config.DebuggerMVCConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 *
 * @author karl
 * @date 2018/8/6
 */
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@Import({BeansConfig.class, DebuggerMVCConfiguration.class})
public @interface EnableDebugUI {
}

