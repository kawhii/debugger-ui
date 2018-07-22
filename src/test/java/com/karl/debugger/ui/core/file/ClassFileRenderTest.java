package com.karl.debugger.ui.core.file;

import com.karl.debugger.ui.model.dto.ClassInfo;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author karl
 * @date 2018/7/22
 */
public class ClassFileRenderTest {
    private ClassFileRender fileRender = new ClassFileRender();

    @Test
    public void render() throws Exception {
        ClassInfo classInfo = fileRender.render("com/karl/debugger/ui/model/dto/MethodInfo.class");
        assertNotNull(classInfo);
    }
}