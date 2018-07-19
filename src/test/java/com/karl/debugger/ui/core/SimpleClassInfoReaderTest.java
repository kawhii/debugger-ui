package com.karl.debugger.ui.core;

import com.karl.debugger.ui.model.dto.ClassInfo;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author karl
 * @date 2018/7/19
 */
public class SimpleClassInfoReaderTest {
    private SimpleClassInfoReader classInfoReader = new SimpleClassInfoReader();

    @Test
    public void read() {
        Class<?> clz = String.class;
        ClassInfo classInfo = classInfoReader.read(clz);
        assertNotNull(classInfo);
        assertEquals(String.class.getName(), classInfo.getClassName());
        assertEquals("public final", classInfo.getModifiersStr());
        assertEquals("java.lang", classInfo.getPackageName());
    }
}