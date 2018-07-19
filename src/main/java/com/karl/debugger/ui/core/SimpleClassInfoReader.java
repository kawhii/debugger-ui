package com.karl.debugger.ui.core;

import com.karl.debugger.ui.model.dto.ClassInfo;
import com.karl.debugger.ui.model.dto.MethodInfo;

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
                .setInterfaceClassNames(getInterfaceNames(clazz))
        ;

        //读取方法数据
        Method[] methods = clazz.getMethods();
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

    /**
     * 获取接口类名
     * @param orgClz
     * @return
     */
    private List<String> getInterfaceNames(Class orgClz) {
        Class<?>[] ifs = orgClz.getInterfaces();
        if(ifs == null) {
            return null;
        }

        List<String> ifsStr = new ArrayList<>(ifs.length);
        for(Class<?> clz : ifs) {
            ifsStr.add(clz.getName());
        }

        return ifsStr;
    }
}
