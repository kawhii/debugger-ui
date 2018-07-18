package com.karl.debugger.ui.core;

import com.karl.debugger.ui.model.dto.InvokeClassDTO;
import org.junit.Test;

import java.util.Base64;

import static org.junit.Assert.*;

/**
 * @author karl
 * @date 2018/7/18
 */
public class Base64InvokerClassResolverTest {
    private Base64InvokerClassResolver classResolver = new Base64InvokerClassResolver();

    @Test
    public void resolve() {
        String className = Base64.getEncoder().encodeToString("java.lang.String".getBytes());
        String type = Base64.getEncoder().encodeToString("java.lang.Long,java.lang.Integer".getBytes());
        String method = "toString";
        InvokeClassDTO classDTO =  classResolver.resolve(className, method, type);
        assertNotNull(classDTO);
        assertEquals("toString", classDTO.getMethodName());
        assertEquals("java.lang.String", classDTO.getClassName());
        assertEquals(2, classDTO.getTypes().size());
    }
}