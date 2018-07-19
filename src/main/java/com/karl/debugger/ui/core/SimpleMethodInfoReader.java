package com.karl.debugger.ui.core;

import com.karl.debugger.ui.model.dto.ArgsInfo;
import com.karl.debugger.ui.model.dto.MethodInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * 简单的方法读取信息
 *
 * @author karl
 * @date 2018/7/19
 */
public class SimpleMethodInfoReader implements IMethodInfoReader {
    /**
     * 参数读取器
     */
    private IMethodArgsInfoReader argsInfoReader = new SimpleMethodArgsInfoReader();
    @Override
    public MethodInfo read(Method method) {
        MethodInfo methodInfo = new MethodInfo();
        //修饰符
        int mds = method.getModifiers();
        methodInfo.setModifiersVal(mds)
                .setModifiersStr(Modifier.toString(mds))
                .setMethodName(method.getName())
                .setReturnType(method.getReturnType() != null ? method.getReturnType().getName() : null)
                .setThrowsTypes(getTypes(method.getExceptionTypes()))
                .setAnnotations(getAnns(method.getDeclaredAnnotations()));

        //参数
        if(argsInfoReader != null) {
            TypeVariable<Method>[] vars = method.getTypeParameters();
            if(vars != null && vars.length > 0) {
               List<ArgsInfo> argsInfos = new ArrayList<>(vars.length);
               for(TypeVariable<Method> variable : vars) {
                   //读取变量
                   argsInfos.add(argsInfoReader.read(variable));
               }
            }
        }
        return methodInfo;
    }

    public void setArgsInfoReader(IMethodArgsInfoReader argsInfoReader) {
        this.argsInfoReader = argsInfoReader;
    }

    /**
     * 根据类获取类名
     * @param classes
     * @return
     */
    private List<String> getTypes(Class<?>[] classes) {
        if(classes == null && classes.length > 0) {
            return null;
        }

        List<String> res = new ArrayList<>(classes.length);
        for(Class<?> clz : classes) {
            res.add(clz.getName());
        }
        if(res.size() == 0) {
            return null;
        }
        return res;
    }

    /**
     * 获取注解信息
     * @param anns
     * @return
     */
    private List<String> getAnns(Annotation[] anns) {
        if(anns == null && anns.length > 0) {
            return null;
        }

        List<String> res = new ArrayList<>(anns.length);
        for(Annotation ann : anns) {
            res.add(ann.annotationType().getName());
        }
        return res;
    }
}
