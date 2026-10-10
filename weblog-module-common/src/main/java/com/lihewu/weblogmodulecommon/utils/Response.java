package com.lihewu.weblogmodulecommon.utils;

import lombok.Data;

/**
 * 统一接口响应，包含执行结果、响应数据及失败信息。
 *
 * @param <T> 响应数据类型
 */
@Data
public class Response<T> {

    /** 是否请求成功。 */
    private boolean success;

    /** 服务端响应数据。 */
    private T data;

    /** 失败原因，成功响应时为 null。 */
    private String message;

    /** 异常码，可采用服务标识加异常编号的格式。 */
    private String errorCode;

    public static <T> Response<T> success() {
        return success(null);
    }

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> fail() {
        return fail("请求失败");
    }

    public static <T> Response<T> fail(String message) {
        return fail(null, message);
    }

    public static <T> Response<T> fail(String errorCode, String message) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setErrorCode(errorCode);
        return response;
    }
}
