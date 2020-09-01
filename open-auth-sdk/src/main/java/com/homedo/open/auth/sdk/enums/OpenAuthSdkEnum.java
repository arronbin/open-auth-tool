package com.homedo.open.auth.sdk.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: eric
 * @Date: 2020/8/25
 * @Description: 开放平台鉴权sdk枚举定义
 */
public enum OpenAuthSdkEnum{

    B_ACCESSKEY_NOT_EXIST("B_ACCESSKEY_NOT_EXIST", "ACCESSKEY不存在"),
    B_SIGNATURE_IS_INCORRECT("B_SIGNATURE_IS_INCORRECT", "签名有误"),
    B_VERSION_NOT_SUPPORTED("B_VERSION_NOT_SUPPORTED", "签名版本不支持"),
    B_MD5_IS_INCORRECT("B_MD5_IS_INCORRECT", "MD5校验有误"),
    B_ACCOUNT_NOT_EXIST("B_ACCOUNT_NOT_EXIST", "账户不存在"),
    B_ACCOUNT_INVALID("B_ACCOUNT_INVALID", "账户无效"),


    P_AUTHORIZATION_FORMAT_IS_INCORRECT("P_AUTHORIZATION_FORMAT_IS_INCORRECT", "授权格式有误"),
    P_AUTHORIZATION_CAN_NOT_BE_EMPTY("P_AUTHORIZATION_CAN_NOT_BE_EMPTY", "授权不能为空"),
    P_ACCESSKEY_CAN_NOT_BE_EMPTY("P_ACCESSKEY_CAN_NOT_BE_EMPTY", "ACCESSKEY不能为空"),
    P_HTTP_METHOD_CAN_NOT_BE_EMPTY("P_HTTP_METHOD_CAN_NOT_BE_EMPTY", "HTTP_METHOD不能为空"),
    P_RESOURCE_CAN_NOT_BE_EMPTY("P_RESOURCE_CAN_NOT_BE_EMPTY", "访问资源不能为空"),
    P_CANONICALIZEDRESOURCE_CAN_NOT_BE_EMPTY("P_CANONICALIZEDRESOURCE_CAN_NOT_BE_EMPTY", "CanonicalizedResource不能为空"),
    P_INCORRECT_ACCESS_TO_RESOURCES("P_INCORRECT_ACCESS_TO_RESOURCES", "访问资源有误"),
    P_DATE_FORMAT_IS_INCORRECT("P_DATE_FORMAT_IS_INCORRECT", "操作日期格式有误"),
    P_DATE_ERROR("P_DATE_ERROR", "操作日期错误"),
    P_DATE_CAN_NOT_BE_EMPTY("P_DATE_CAN_NOT_BE_EMPTY", "操作日期不能为空"),;

    private String code;
    private String msg;

    private OpenAuthSdkEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
