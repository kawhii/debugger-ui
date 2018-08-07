package com.karl.debugger.ui.core.strategy;

import com.karl.debugger.ui.core.IInstanceStrategy;
import com.karl.debugger.ui.core.exception.InstanceException;

import java.util.List;

/**
 * 代理策略
 * @author karl
 * @date 2018/8/7
 */
public class DelegateInstanceStrategy implements IInstanceStrategy {
    /**
     * 代理策略器集合
     */
    private List<IInstanceStrategy> strategies;

    public DelegateInstanceStrategy(List<IInstanceStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public Object getInstance(Class<?> clazz, Object... args) throws InstanceException {
        //默认找不到策略异常
        InstanceException exception = null;
        for (IInstanceStrategy strategy : strategies) {
            try {
                Object res =  strategy.getInstance(clazz, args);
                if(res != null) {
                    return res;
                }
            } catch (InstanceException e) {
                exception = new InstanceException(e);
            }
        }
        //若为空给予默认的异常
        if(exception == null) {
            throw new InstanceException("not found IInstanceStrategy", null);
        }

        throw exception;
    }
}
