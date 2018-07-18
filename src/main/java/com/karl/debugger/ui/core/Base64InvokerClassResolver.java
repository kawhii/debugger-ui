package com.karl.debugger.ui.core;

import com.karl.debugger.ui.model.dto.InvokeClassDTO;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Base64;

/**
 * base64方法解析
 *
 * @author karl
 * @date 2018/7/18
 */
public class Base64InvokerClassResolver implements IInvokerClassResolver {
    @Override
    public InvokeClassDTO resolve(String clazz, String method, String types) {
        InvokeClassDTO classDTO = new InvokeClassDTO();
        String className = new String(Base64.getDecoder().decode(clazz));
        classDTO.setClassName(className)
                .setMethodName(method);
        if (!StringUtils.isEmpty(types)) {
            String[] typesList = new String(Base64.getDecoder().decode(types)).split(",");
            classDTO.setTypes(Arrays.asList(typesList));
        }
        return classDTO;
    }
}
