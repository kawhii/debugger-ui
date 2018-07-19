package com.karl.debugger.ui.core;

import com.karl.debugger.ui.model.dto.ClassTargetDTO;
import com.karl.debugger.ui.model.dto.InvokeClassDTO;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author karl
 * @date 2018/7/19
 */
public class ClassForNameTargetClassResolverTest {
    private ClassForNameTargetClassResolver classResolver = new ClassForNameTargetClassResolver();

    @Test
    public void resolve() throws ClassNotFoundException {
        InvokeClassDTO invokeClass = new InvokeClassDTO();
        invokeClass.setClassName("java.lang.String");
        invokeClass.setMethodName("equals");
        invokeClass.setTypes(Arrays.asList("java.lang.Object"));

        ClassTargetDTO classTarget = classResolver.resolve(invokeClass);
        assertNotNull(classTarget);
        assertEquals("java.lang.String", classTarget.getClazz().getName());
        assertEquals("equals", classTarget.getMethodName());
        assertEquals(1, classTarget.getTypes().size());
    }

    @Test(expected = ClassNotFoundException.class)
    public void resolveWithException() throws ClassNotFoundException {
        InvokeClassDTO invokeClass = new InvokeClassDTO();
        invokeClass.setClassName("java.lang.abc.d");
        invokeClass.setMethodName("equals");
        invokeClass.setTypes(Arrays.asList("java.lang.Object"));
        classResolver.resolve(invokeClass);
    }
}