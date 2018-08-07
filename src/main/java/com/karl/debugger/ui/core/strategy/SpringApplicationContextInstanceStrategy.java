package com.karl.debugger.ui.core.strategy;

import com.karl.debugger.ui.core.IInstanceStrategy;
import com.karl.debugger.ui.core.exception.InstanceException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * 采用spring的方式加载实例
 *
 * @author karl
 * @date 2018/7/19
 */
public class SpringApplicationContextInstanceStrategy implements IInstanceStrategy {
    /**
     * 注入spring的context
     */
    private ApplicationContext context;

    @Override
    public Object getInstance(Class<?> clazz, Object... args) throws InstanceException {
        try {
            return context.getBean(clazz);
        } catch (BeansException e) {
            throw new InstanceException(e);
        }
    }

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
