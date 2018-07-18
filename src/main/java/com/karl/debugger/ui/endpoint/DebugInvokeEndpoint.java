package com.karl.debugger.ui.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{clazz}.{method}")
    public Object invoke(
            //执行类名，需要将类名进行base64("com.karl.debugger.ui.service.impl.FileServiceImpl")
            @PathVariable("clazz") String clz,
            //执行方法名
            @PathVariable("method") String method,
            //方法类型，如果有函数，则需要进行base64("java.lang.String,java.lang.Integer")
            @RequestParam(required = false, value = "types") String types) throws Exception {
//        String clz = "com.karl.debugger.ui.service.impl.FileServiceImpl";
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
