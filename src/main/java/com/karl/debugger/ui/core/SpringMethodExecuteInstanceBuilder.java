package com.karl.debugger.ui.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karl.debugger.ui.core.exception.InstanceException;
import com.karl.debugger.ui.core.exception.MethodInstanceBuilderException;
import com.karl.debugger.ui.model.dto.MethodExecuteInstance;
import com.karl.debugger.ui.model.dto.MethodExecuteOriginal;
import com.karl.debugger.ui.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法实例构造器
 *
 * @author karl
 * @date 2018/8/4
 */
@Component
public class SpringMethodExecuteInstanceBuilder implements IMethodExecuteInstanceBuilder {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private SpringApplicationContextInstanceStrategy instanceStrategy;

    @Override
    public MethodExecuteInstance build(MethodExecuteOriginal original) throws MethodInstanceBuilderException, InstanceException {
        MethodExecuteInstance instance = new MethodExecuteInstance();
        //获取类名
        Class<?> clazz;
        try {
            clazz = ClassUtils.forName(original.getClassName(), null);
        } catch (ClassNotFoundException e) {
            throw new MethodInstanceBuilderException(e, original);
        }
        //执行实例
        instance.setInstance(instanceStrategy.getInstance(clazz));

        //参数类型
        List<Class<?>> types ;
        try {
            types = getTypes(original.getParamsTypes());
        } catch (ClassNotFoundException e) {
            throw new MethodInstanceBuilderException(e, original);
        }
        Class<?>[] typesArr = types != null ? types.toArray(new Class[]{}) : null;

        //执行方法
        Method method;
        try {
            //获取自身方法
            method = clazz.getDeclaredMethod(original.getMethodName(), typesArr);
        } catch (NoSuchMethodException e) {
            throw new MethodInstanceBuilderException(e, original);
        }
        instance.setMethod(method);

        try {
            instance.setArgs(getParamsInstance(original.getParamsValue(), types));
        } catch (IOException e) {
            throw new InstanceException(e);
        }
        return instance;
    }

    /**
     * 根据参数名获取参数实例
     *
     * @param params     入参，为字符串
     * @param paramTypes 方法参数类型
     * @return
     * @throws IOException
     */
    private List<Object> getParamsInstance(List<String> params, List<Class<?>> paramTypes) throws IOException {
        List<Object> paramsInstance = new ArrayList<>();
        if (params != null && paramTypes != null && paramTypes.size() >= params.size()) {
            for (int i = 0, len = params.size(); i < len; i++) {
                Object val;
                try {
                    //若解析失败则为对象，需要采用json进行转换
                    val = PropertiesUtil.parseValueFromString(params.get(i), paramTypes.get(i));
                } catch (Exception e) {
                    val = objectMapper.readValue(params.get(i), paramTypes.get(i));
                }
                paramsInstance.add(val);
            }
        }
        return paramsInstance;
    }

    /**
     * 根据入参获取参数类型，进行base64解密再进行获取
     *
     * @param types 参数类型
     * @return
     * @throws ClassNotFoundException
     */
    private List<Class<?>> getTypes(List<String> types) throws ClassNotFoundException {
        if (!StringUtils.isEmpty(types)) {
            List<Class<?>> ts = new ArrayList<>(types.size());
            for (String t : types) {
                Class<?> type = ClassUtils.forName(t, null);
                ts.add(type);
                return ts;
            }
        }
        return null;
    }
}
