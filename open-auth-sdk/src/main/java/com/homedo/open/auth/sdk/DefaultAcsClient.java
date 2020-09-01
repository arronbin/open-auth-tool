package com.homedo.open.auth.sdk;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.homedo.open.auth.sdk.profile.DefaultProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: eric
 * @Date: 2020/8/26
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultAcsClient implements IAcsClient{

    private DefaultProfile profile;

    @Override
    public <T extends AcsResponse> T getAcsResponse(AcsRequest request){
        return (T)DefaultHttpClient.acsResponse(request,Method.GET,profile);
    }

    @Override
    public <T extends AcsResponse> T postAcsResponse(AcsRequest request){
        return (T)DefaultHttpClient.acsResponse(request,Method.POST,profile);
    }

    @Override
    public <T extends AcsResponse> T getAcsResponse(AcsRequest request, Class clz){
        DefaultResponse result = (DefaultResponse)DefaultHttpClient.acsResponse(request,Method.GET,profile);
        if(ObjectUtil.isNotEmpty(result.getData())){
            result.setData(JSON.parseObject(result.getData().toString(),clz));
        }
        return (T)result;
    }

    @Override
    public <T extends AcsResponse> T postAcsResponse(AcsRequest request, Class clz) {
        DefaultResponse result = (DefaultResponse)DefaultHttpClient.acsResponse(request,Method.GET,profile);
        if(ObjectUtil.isNotEmpty(result.getData())){
            result.setData(JSON.parseObject(result.getData().toString(),clz));
        }
        return (T)result;
    }
}
