package com.karl.debugger.ui.core;

import com.karl.debugger.ui.ClassUtils;
import com.karl.debugger.ui.model.dto.ArgsInfo;
import com.karl.debugger.ui.model.dto.MethodInfo;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
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
                .setThrowsTypes(ClassUtils.getClassNames(method.getExceptionTypes()))
                .setAnnotations(ClassUtils.getAnnotationTypes(method.getDeclaredAnnotations()));

        //参数
        if(argsInfoReader != null) {
            Parameter[] parameters = method.getParameters();
            if(parameters != null && parameters.length > 0) {
               List<ArgsInfo> argsInfos = new ArrayList<>(parameters.length);
               for(Parameter parameter : parameters) {
                   //读取变量
                   argsInfos.add(argsInfoReader.read(parameter));
               }
               methodInfo.setArgs(argsInfos);
            }
        }
        return methodInfo;
    }

    public void setArgsInfoReader(IMethodArgsInfoReader argsInfoReader) {
        this.argsInfoReader = argsInfoReader;
    }


}
