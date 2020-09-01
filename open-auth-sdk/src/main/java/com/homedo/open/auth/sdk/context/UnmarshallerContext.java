package com.homedo.open.auth.sdk.context;

import cn.hutool.http.HttpResponse;
import lombok.Data;

import java.util.Map;

/**
 * @Auther: eric
 * @Date: 2020/8/26
 * @Description:
 */
@Data
public class UnmarshallerContext {
    private int httpStatus;
    private Map<String, String> responseMap;
    private HttpResponse httpResponse;
}
