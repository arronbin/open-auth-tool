package com.homedo.open.auth.sdk.exception;

import com.homedo.open.auth.sdk.enums.OpenAuthSdkEnum;

/**
 * @Auther: eric
 * @Date: 2020/8/25
 * @Description: 开放平台鉴权sdk异常定义
 */
public class OpenAuthSdkException extends RuntimeException{

    private String code;

    public OpenAuthSdkException(OpenAuthSdkEnum sdkEnum) {
        super(sdkEnum.getMsg());
        code = sdkEnum.getCode();
    }

    public String getCode() {
        return code;
    }
}
