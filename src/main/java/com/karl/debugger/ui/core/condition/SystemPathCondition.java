package com.karl.debugger.ui.core.condition;


import org.springframework.boot.ApplicationHome;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 路径jar方式启动
 *
 * @author karl
 * @date 2018/8/6
 */
public class SystemPathCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ApplicationHome home = new ApplicationHome(new SpringApplication().getMainApplicationClass());
        return home.getSource() == null || home.getSource().isDirectory();
    }
}
