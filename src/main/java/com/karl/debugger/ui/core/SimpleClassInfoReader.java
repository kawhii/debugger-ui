package com.karl.debugger.ui.core;

import com.karl.debugger.ui.model.dto.ClassInfo;
import com.karl.debugger.ui.model.dto.MethodInfo;
import com.karl.debugger.ui.utils.ClassUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 简单的读取类信息
 *
 * @author karl
 * @date 2018/7/19
 */
public class SimpleClassInfoReader implements IClassInfoReader {
    /**
     * 方法读取器
     */
    private IMethodInfoReader methodInfoReader = new SimpleMethodInfoReader();
    @Override
    public ClassInfo read(Class<?> clazz) {
        ClassInfo classInfo = new ClassInfo();
        //修饰符
        int mds = clazz.getModifiers();
        //父类
        Class superClz = clazz.getSuperclass();
        classInfo.setClassName(clazz.getName())
                .setPackageName(clazz.getPackage().getName())
                .setModifiersVal(mds)
                .setModifiersStr(Modifier.toString(mds))
                .setSuperClassName(superClz != null ? superClz.getName(): null)
                .setInterfaceClassNames(ClassUtils.getClassNames(clazz.getInterfaces()))
                .setInterface(clazz.isInterface())
                .setEnum(clazz.isEnum())
                .setAnnotation(clazz.isAnnotation())
        ;

        //读取方法数据
        Method[] methods = clazz.getDeclaredMethods();
        if(methodInfoReader != null && methods != null) {
            List<MethodInfo> methodInfos = new ArrayList<>(methods.length);
            for(Method method : methods) {
                //读取
                methodInfos.add(methodInfoReader.read(method));
            }
            if(methodInfos.size() > 0) {
                classInfo.setMethods(methodInfos);
            }
        }
        return classInfo;
    }
}
