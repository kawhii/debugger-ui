package com.karl.debugger.ui.core;

import com.karl.debugger.ui.DebuggerUiApplication;
import com.karl.debugger.ui.model.dto.MethodExecuteInstance;
import com.karl.debugger.ui.model.dto.MethodExecuteOriginal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author karl
 * @date 2018/8/4
 */
@SpringBootTest(classes = DebuggerUiApplication.class)
@RunWith(SpringRunner.class)
public class SpringMethodExecuteInstanceBuilderTest {
    @Autowired
    private IMethodExecuteInstanceBuilder instanceBuilder;

    private MethodExecuteOriginal methodExecute;
    @Before
    public void setUp() {
        //方法执行原始值
        methodExecute = new MethodExecuteOriginal();
        methodExecute.setClassName("com.karl.debugger.ui.service.impl.SystemFileServiceImpl")
                .setMethodName("list")
                .setParamsTypes(Arrays.asList("java.lang.String"))
                .setParamsValue(Arrays.asList("test-value"));

    }

    @Test
    public void build() throws Exception {
//        MethodExecuteInstance obj = instanceBuilder.build(methodExecute);
//
//        assertNotNull(obj);
    }
}