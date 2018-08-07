package com.karl.debugger.ui.core.strategy;

import com.karl.debugger.ui.core.IInstanceStrategy;
import com.karl.debugger.ui.core.exception.InstanceException;
import org.springframework.stereotype.Component;

/**
 * 构造函数进行获取实例
 *
 * @author karl
 * @date 2018/8/7
 */
@Component
public class ConstructInstanceStrategy implements IInstanceStrategy {
    @Override
    public Object getInstance(Class<?> clazz, Object... args) throws InstanceException {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new InstanceException(e);
        }
    }
}
