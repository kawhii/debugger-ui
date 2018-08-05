package com.karl.debugger.ui.core;

import com.karl.debugger.ui.core.exception.MethodInvokeException;
import com.karl.debugger.ui.model.dto.MethodExecuteInstance;
import org.springframework.stereotype.Component;


/**
 * 默认的方法执行
 *
 * @author karl
 * @date 2018/8/5
 */
@Component
public class DefaultMethodInvoker implements IMethodInvoker {
    @Override
    public Object invoke(MethodExecuteInstance instance) throws MethodInvokeException {
        Object[] args = (instance.getArgs() != null && instance.getArgs().size() > 0) ? instance.getArgs().toArray() : null;
        try {
            return instance.getMethod().invoke(instance.getInstance(), args);
        } catch (Exception e) {
            throw new MethodInvokeException(e, instance);
        }
    }
}
