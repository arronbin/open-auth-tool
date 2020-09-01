package com.homedo.open.auth.sdk;

import cn.hutool.core.bean.BeanUtil;
import com.homedo.open.auth.sdk.model.basic.BaseModel;
import lombok.*;

import java.util.Map;

/**
 * @Auther: eric
 * @Date: 2020/8/26
 * @Description:
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DefaultRequst extends AcsRequest{

    //超时时间 毫秒级别
    private Integer timeout;

    /** 请求内容数据,可为空**/
    private Object bodyContent;

    /** http所请求资源的URI 必填**/
    private String resource;

    /** 用户自定义head参数，可为空 **/
    private Map<String,String> customHeaders;

    @Override
    BaseModel buildBaseModel(AcsRequest requst) {
        BaseModel model = new BaseModel();
        BeanUtil.copyProperties(requst,model);
        return model;
    }
}
