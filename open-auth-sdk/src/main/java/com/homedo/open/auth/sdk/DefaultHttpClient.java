package com.homedo.open.auth.sdk;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.*;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.homedo.open.auth.sdk.model.business.BusinessDataModel;
import com.homedo.open.auth.sdk.profile.DefaultProfile;
import com.homedo.open.auth.sdk.utils.OpenAuthSdkUtil;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: eric
 * @Date: 2020/8/26
 * @Description: 默认Http请求封装
 */
@UtilityClass
public class DefaultHttpClient {

    public <T extends AcsResponse> T acsResponse(AcsRequest<T> request,Method method, DefaultProfile profile){
        DefaultRequst defaultRequst = (DefaultRequst)request;
        String uri = StrUtil.format("{}{}",profile.getAddress(),defaultRequst.getResource());
        BusinessDataModel businessDataModel = BeanUtil.toBean(defaultRequst,BusinessDataModel.class);
        businessDataModel.setHttpMethod(method.name());
        Map<String,String> headerMap =  OpenAuthSdkUtil.buildHttpHeadersMap(businessDataModel,profile);
        Map<String, List<String>>headersMap = headerMap.entrySet()
                .stream()
                .collect(Collectors.toMap(m1->m1.getKey(), m2-> Lists.newArrayList(m2.getValue()),(m1, m2)->m1));
        Object bodyContent = businessDataModel.getBodyContent();
        String content = ObjectUtil.isNotEmpty(bodyContent)?JSON.toJSONString(bodyContent):null;
        HttpRequest httpRequest;
        if(StrUtil.equals(method.name(),Method.GET.name())){
            httpRequest = HttpRequest.get(uri).header(headersMap);
        }else{
            httpRequest = HttpRequest.post(uri).header(headersMap);
            if(StrUtil.isNotBlank(content)){
                httpRequest.body(content);
            }
        }
        //处理用户自定义head
        if(CollUtil.isNotEmpty(businessDataModel.getCustomHeaders())){
            businessDataModel.getCustomHeaders().entrySet()
                    .parallelStream().forEach(f->httpRequest.header(f.getKey(),f.getValue()));
        }
        if(null != businessDataModel.getTimeout()){
            httpRequest.timeout(businessDataModel.getTimeout());
        }
        HttpResponse response = httpRequest.execute();
        if(response.getStatus() == HttpStatus.HTTP_MOVED_PERM||response.getStatus() == HttpStatus.HTTP_MOVED_TEMP||response.getStatus() == 308){
            List<String> locations =  response.headerList("location");
            if(CollUtil.isNotEmpty(locations)){
                httpRequest.setUrl(locations.get(0));
                response = httpRequest.execute();
            }
        }
        if(response.getStatus() == HttpStatus.HTTP_OK){
           String body = response.body();
           DefaultResponse<?> defaultResponse = JSON.parseObject(body,DefaultResponse.class);
           return (T)defaultResponse;
        }else{
           return (T)DefaultResponse.builder().respCode(response.getStatus()+"").build();
        }
    }
}
