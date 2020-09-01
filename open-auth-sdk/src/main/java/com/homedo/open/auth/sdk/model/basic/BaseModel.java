package com.homedo.open.auth.sdk.model.basic;

import lombok.Data;
import java.util.Map;

/**
 * @Auther: eric
 * @Date: 2020/8/25
 * @Description:
 */
@Data
public class BaseModel {

    //超时时间 毫秒级别
    private Integer timeout;

    /** HTTP请求方式 PUT,GET,POST,DELETE,须大写 必填**/
    private String httpMethod;

    /** 请求内容数据,可为空**/
    private Object bodyContent;

    /** http所请求资源的URI 必填**/
    private String resource;

    /** 用户自定义head参数，可为空 **/
    private Map<String,String> customHeaders;

}
