package com.karl.debugger.ui.core;

import com.karl.debugger.ui.model.dto.MethodInfo;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @author karl
 * @date 2018/7/19
 */
public class SimpleMethodInfoReaderTest {
    private SimpleMethodInfoReader methodInfoReader = new SimpleMethodInfoReader();

    @Test
    public void read() throws NoSuchMethodException {
        Method method = String.class.getMethod("toString");
        MethodInfo methodInfo = methodInfoReader.read(method);
        assertNotNull(methodInfo);
        assertEquals("toString", methodInfo.getMethodName());
        assertEquals("public", methodInfo.getModifiersStr());
        assertEquals("java.lang.String", methodInfo.getReturnType());
        assertNull(methodInfo.getThrowsTypes());
    }
}