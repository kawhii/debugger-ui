package com.karl.debugger.ui.endpoint;

import com.karl.debugger.ui.core.exception.InstanceException;
import com.karl.debugger.ui.core.exception.MethodInstanceBuilderException;
import com.karl.debugger.ui.core.exception.MethodInvokeException;
import com.karl.debugger.ui.model.dto.EndpointResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 抽象的接口调用，主要负责上层统一异常
 *
 * @author karl
 * @date 2018/8/5
 */
@ControllerAdvice
public abstract class BaseEndpoint {
    /**
     * 方法执行异常时调用
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodInvokeException.class)
    public EndpointResponse<?> invokeException(MethodInvokeException e) {

        return EndpointResponse.fail("方法执行异常[" + exceptionToString(e) + "]" ,
                e.getInstance().getArgs());

    }

    @ExceptionHandler(MethodInstanceBuilderException.class)
    public EndpointResponse<?> methodInstanceBuilderException(MethodInstanceBuilderException e) {
        return EndpointResponse.fail("构建方法实例异常[" + exceptionToString(e) + "]");
    }

    @ExceptionHandler(InstanceException.class)
    public EndpointResponse<?> instanceException(InstanceException e) {
        return EndpointResponse.fail("构建执行实例异常-[" + exceptionToString(e) + "]");
    }

    @ExceptionHandler(IOException.class)
    public EndpointResponse<?> ioException(IOException e) {
        e.printStackTrace();
        return EndpointResponse.fail(500, "出现io异常-[" + exceptionToString(e) + "]");
    }

    @ExceptionHandler(Throwable.class)
    public EndpointResponse<?> ioException(Throwable e) {
        e.printStackTrace();
        return EndpointResponse.fail(500, "系统异常[" + exceptionToString(e) + "]");
    }

    /**
     * 把错误信息转成字符串
     * @param throwable
     * @return
     */
    private String exceptionToString(Throwable throwable) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.toByteArray();
        throwable.printStackTrace(new PrintStream(outputStream));
        return new String(outputStream.toByteArray());
    }
}
