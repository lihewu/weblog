package com.lihewu.weblogmodulecommon.enums;

import com.lihewu.weblogmodulecommon.exception.BaseExceptionInterface;

/**
 * 统一响应异常码，后续可按业务场景扩展。
 */
public enum ResponseCodeEnum implements BaseExceptionInterface {

    SYSTEM_ERROR("10000", "出错啦，后台正在努力修复中..."),
    PARAM_NOT_VALID("10001", "参数错误");

    private final String errorCode;
    private final String errorMessage;

    ResponseCodeEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
