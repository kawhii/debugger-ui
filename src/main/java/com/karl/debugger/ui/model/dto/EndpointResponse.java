package com.karl.debugger.ui.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 接口响应值
 *
 * @author karl
 * @date 2018/8/5
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EndpointResponse<T> {
    /**
     * 默认的成功编码
     */
    public static final Integer SUCCESS_CODE = 0;
    /**
     * 默认的错误编码
     */
    public static final Integer FAIL_CODE = -1;
    /**
     * 响应值
     */
    private int code;
    /**
     * 消息
     */
    private String message;
    /**
     * 响应内容
     */
    private T body;

    public int getCode() {
        return code;
    }

    public EndpointResponse<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public EndpointResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getBody() {
        return body;
    }

    public EndpointResponse<T> setBody(T body) {
        this.body = body;
        return this;
    }

    /**
     * 成功，携带默认信息
     *
     * @return
     */
    public static EndpointResponse<?> success() {
        return success("success");
    }

    /**
     * 成功携带响应信息
     * @param body
     * @param <T>
     * @return
     */
    public static <T> EndpointResponse<T> success(T body) {
        return success("success", body);
    }

    /**
     * 成功自定义消息
     *
     * @param message
     * @return
     */
    public static EndpointResponse<?> success(String message) {
        return success(message, null);
    }

    /**
     * 成功自定义消息以及body
     *
     * @param message 消息内容
     * @param body    body
     * @param <T>
     * @return
     */
    public static <T> EndpointResponse<T> success(String message, T body) {
        return new EndpointResponse<T>().setCode(SUCCESS_CODE).setMessage(message).setBody(body);
    }

    /**
     * 默认错误
     *
     * @return
     */
    public static EndpointResponse<?> fail() {
        return fail("fail");
    }

    /**
     * 错误携带错误消息
     *
     * @param message 错误信息
     * @return
     */
    public static EndpointResponse<?> fail(String message) {
        return fail(FAIL_CODE, message);
    }

    /**
     * 错误携带错误码以及信息
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> EndpointResponse<T> fail(int code, String message) {
        return fail(code, message, null);
    }

    /**
     * 错误携带消息以及响应内容
     *
     * @param message
     * @param body
     * @param <T>
     * @return
     */
    public static <T> EndpointResponse<T> fail(String message, T body) {
        return fail(FAIL_CODE, message, body);
    }

    /**
     * 错误携带编码，信息以及响应内容
     *
     * @param code
     * @param message
     * @param body
     * @param <T>
     * @return
     */
    public static <T> EndpointResponse<T> fail(int code, String message, T body) {
        return new EndpointResponse<T>().setCode(code).setMessage(message).setBody(body);
    }
}
