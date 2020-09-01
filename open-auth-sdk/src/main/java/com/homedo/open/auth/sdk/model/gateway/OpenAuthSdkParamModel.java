package com.homedo.open.auth.sdk.model.gateway;

import com.homedo.open.auth.sdk.model.basic.BaseModel;
import lombok.Data;

/**
 * @Auther: eric
 * @Date: 2020/8/25
 * @Description: 网关鉴权参数实体类
 */
@Data
public class OpenAuthSdkParamModel extends BaseModel {

    /** 此次操作时间,格式为：Thu, 07 Mar 2012 18:49:58 GMT 必填**/
    private String dateGMTStr;

    /**请求内容数据是否启动Md5加密,默认不启用**/
    private String contentMd5;

    /** 请求内容的类型 如application/json;charset=UTF-8 可为空**/
    private String contentType;
}
