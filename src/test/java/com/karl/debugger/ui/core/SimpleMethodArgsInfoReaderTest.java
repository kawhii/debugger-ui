package com.karl.debugger.ui.core;

import com.karl.debugger.ui.model.dto.ArgsInfo;
import org.junit.Test;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;

import static org.junit.Assert.*;

/**
 * @author karl
 * @date 2018/7/19
 */
public class SimpleMethodArgsInfoReaderTest {
    private SimpleMethodArgsInfoReader argsInfoReader = new SimpleMethodArgsInfoReader();

    @Test
    public void read() throws NoSuchMethodException {
        Method testMethod = TestClass.class.getMethod("testMethod", String.class);
        Parameter parameter = testMethod.getParameters()[0];
        ArgsInfo argsInfo = argsInfoReader.read(parameter);
        assertNotNull(argsInfo);
        assertEquals("java.lang.String", argsInfo.getType());
        assertEquals(1, argsInfo.getAnnotations().size());
    }

    @Test
    public void noAnn() throws NoSuchMethodException {
        Method testMethod = String.class.getMethod("valueOf", int.class);
        Parameter parameter = testMethod.getParameters()[0];
        ArgsInfo argsInfo = argsInfoReader.read(parameter);
        assertNotNull(argsInfo);
        assertNotNull(argsInfo.getAnnotations());
        assertEquals("int", argsInfo.getType());
    }


    public class TestClass {
        public void testMethod(@TestAnnotation String name) {

        }
    }

    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface TestAnnotation {

    }
}