package com.karl.debugger.ui.endpoint;

import com.karl.debugger.ui.core.IMethodExecuteInstanceBuilder;
import com.karl.debugger.ui.model.dto.MethodExecuteInstance;
import com.karl.debugger.ui.model.dto.MethodExecuteOriginal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 执行操作
 *
 * @author karl
 * @date 2018/7/18
 */
@RestController
@RequestMapping("/dg/invoke")
public class DebugInvokeEndpoint {
    @Autowired
    private IMethodExecuteInstanceBuilder instanceBuilder;

    @PostMapping("/{clazz}/{method}")
    public Object invoke(
            //执行类名，需要将类名进行base64("com.karl.debugger.ui.service.impl.FileServiceImpl")
            @PathVariable("clazz") String clz,
            //执行方法名
            @PathVariable("method") String method,
            //方法类型，如果有函数，则需要进行base64("java.lang.String,java.lang.Integer")
            @RequestParam(required = false, value = "types") String types,
            //执行方法参数
            @RequestBody(required = false) List<String> params
    ) throws Exception {
        //类名
        String clazzName = new String(Base64Utils.decodeFromString(clz));
        //方法名
        String methodName = new String(Base64Utils.decodeFromString(method));

        //方法执行原始值
        MethodExecuteOriginal methodExecute = new MethodExecuteOriginal();
        methodExecute.setClassName(clazzName)
                .setMethodName(methodName)
                .setParamsTypes(getParamsType(types))
                .setParamsValue(params);

        MethodExecuteInstance obj = instanceBuilder.build(methodExecute);
        //todo 执行方法响应
        return clazzName + "." + methodName + "." + types;
    }

    /**
     * 转换参数类型，字符串，主要负责前端的转码
     * @param typeStr
     * @return
     */
    private List<String> getParamsType(String typeStr) {
        if (!StringUtils.isEmpty(typeStr)) {
            String[] typeArray = StringUtils.tokenizeToStringArray(new String(Base64Utils.decodeFromString(typeStr)), ",");
            return Arrays.asList(typeArray);

        }
        return null;
    }




}
