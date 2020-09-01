package com.homedo.open.auth.sdk.test;
import com.google.common.collect.Maps;
import com.homedo.open.auth.sdk.*;
import com.homedo.open.auth.sdk.enums.EnvEnum;
import com.homedo.open.auth.sdk.profile.DefaultProfile;
import lombok.SneakyThrows;

import java.util.Map;

public class Demo {
    @SneakyThrows
    public static void main(String[] args) {
//        String path = "/member/getMemberInfo";
//        String path = "/privacy/policyNews";
        String path = "/homedo-yonsuite-service/getCategory";
        DefaultProfile profile = DefaultProfile.getProfile("SXdJgvV2xsThScJN","zgJ2f-IsXunxUsaEVF1XP_oSDfs2AE", EnvEnum.FAT.name());
//        DefaultProfile profile = DefaultProfile.getProfile("9b2NTLcEcYZ9d2AF","AL2Rk2UJhrfY-_w4fA-yMnKC8TuAkn", EnvEnum.DEV.name());
//        DefaultProfile profile = DefaultProfile.getProfile("VjGHlYQtx3bZH634","B6BHG1z9dDypIsgiFTCr9rc65micBV", EnvEnum.PRO.name());
        Map<String, String> customHeaders= Maps.newHashMap();
        customHeaders.put("ticket","fat896307e917804e0d86a2361733331e9c-30268-login");
        customHeaders.put("x-identification","1");
        customHeaders.put("x-registrationID","");
        customHeaders.put("x-version","2.0.0");
        customHeaders.put("x-platform","ios");
        IAcsClient client = new DefaultAcsClient(profile);
        DefaultRequst requst = DefaultRequst.builder().resource(path).build();
//        requst.setCustomHeaders(customHeaders);
        DefaultResponse response = client.getAcsResponse(requst);
        System.out.println(response);
    }

}
