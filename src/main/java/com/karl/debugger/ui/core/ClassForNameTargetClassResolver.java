package com.karl.debugger.ui.core;

import com.karl.debugger.ui.model.dto.ClassTargetDTO;
import com.karl.debugger.ui.model.dto.InvokeClassDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过class.forName方式加载类
 *
 * @author karl
 * @date 2018/7/19
 */
public class ClassForNameTargetClassResolver implements ITargetClassResolver {
    @Override
    public ClassTargetDTO resolve(InvokeClassDTO invokeClass) throws ClassNotFoundException {
        ClassTargetDTO classTarget = new ClassTargetDTO();
        classTarget.setMethodName(invokeClass.getMethodName());

        //反射出具体类
        Class<?> clazz = Class.forName(invokeClass.getClassName());
        //反射出获取类名
        classTarget.setClazz(clazz);

        if (invokeClass.getTypes() != null && invokeClass.getTypes().size() > 0) {
            List<Class<?>> clzTypes = new ArrayList<>(invokeClass.getTypes().size());

            //反射出类型
            for (String type : invokeClass.getTypes()) {
                clzTypes.add(Class.forName(type));
            }
            classTarget.setTypes(clzTypes);
        }
        return classTarget;
    }
}
