package com.lihewu.weblogmodulecommon.exception;

/**
 * 基础异常接口，统一异常码和异常信息的获取方式。
 */
public interface BaseExceptionInterface {

    /** 获取异常码。 */
    String getErrorCode();

    /** 获取异常信息。 */
    String getErrorMessage();
}
