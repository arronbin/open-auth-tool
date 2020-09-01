package com.homedo.open.auth.demo.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.homedo.open.auth.sdk.DefaultAcsClient;
import com.homedo.open.auth.sdk.DefaultRequst;
import com.homedo.open.auth.sdk.DefaultResponse;
import com.homedo.open.auth.sdk.IAcsClient;
import com.homedo.open.auth.sdk.enums.EnvEnum;
import com.homedo.open.auth.sdk.model.business.BusinessDataModel;
import com.homedo.open.auth.sdk.profile.DefaultProfile;
import com.homedo.open.auth.sdk.utils.OpenAuthSdkUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

/**
 * @Auther: eric
 * @Date: 2020/8/25
 * @Description:
 */
@RestController

public class DemoController {

    @Autowired
    private RestTemplate restTemplate;

    private final String PATH = "/homedo-yonsuite-service/getCategory";

    private DefaultProfile profile = DefaultProfile.getProfile("SXdJgvV2xsThScJN","zgJ2f-IsXunxUsaEVF1XP_oSDfs2AE", EnvEnum.FAT.name());

    @GetMapping(value = "/demo")
    @ResponseBody
    public String demo() {
        //参数封装
        BusinessDataModel model = new BusinessDataModel();
        model.setHttpMethod(Method.GET.name().toUpperCase());
        model.setResource(PATH);
        //获取请求头Map数据
        Map<String,String>headerMap =  OpenAuthSdkUtil.buildHttpHeadersMap(model,profile);
        HttpHeaders requestHeaders = new HttpHeaders();
        //将请求头Map数据转换
        headerMap.entrySet().stream().forEach(f->requestHeaders.add(f.getKey(), f.getValue()));
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange(StrUtil.format("{}{}",profile.getAddress(),PATH), HttpMethod.GET, requestEntity, String.class);
        return response.getBody();
    }
    @GetMapping(value = "/demo1")
    @ResponseBody
    @SneakyThrows
    public String demo1() {
        IAcsClient client = new DefaultAcsClient(profile);
        DefaultRequst requst = DefaultRequst.builder().resource(PATH).build();
        DefaultResponse response = client.getAcsResponse(requst);
        return JSON.toJSONString(response);
    }
}
