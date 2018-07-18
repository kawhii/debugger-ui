package com.karl.debugger.ui.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

/**
 * 执行操作
 *
 * @author karl
 * @date 2018/7/18
 */
@RestController
@RequestMapping("/dg/invoke")
public class DebugInvokeEndpoint {
    @GetMapping
    public Object invoke() throws Exception {
        String clz = "com.karl.debugger.ui.service.impl.FileServiceImpl";
        Class clazz = Class.forName(clz);
        Method md = clazz.getMethod("list", String.class);
        Object bean = getBean(clazz);
        return md.invoke(bean, "");
    }

    @Autowired
    private ApplicationContext context;
    private Object getBean(Class clazz) throws Exception {
        return context.getBean(clazz);
    }
}
