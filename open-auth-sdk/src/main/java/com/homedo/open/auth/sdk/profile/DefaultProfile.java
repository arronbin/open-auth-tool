package com.homedo.open.auth.sdk.profile;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.http.HttpUtil;
import cn.hutool.setting.dialect.Props;
import com.homedo.open.auth.sdk.enums.EnvEnum;

import java.util.Optional;

/**
 * @Auther: eric
 * @Date: 2020/8/26
 * @Description: 默认环境配置
 */
public class DefaultProfile implements IClientProfile{

    private volatile static DefaultProfile profile = null;

    private String accessKeyId;

    private String secretKey;

    private String address;

    private static HMac mac;

    public DefaultProfile(String accessKeyId, String secretKey,String address) {
        this.accessKeyId = accessKeyId;
        this.secretKey = secretKey;
        this.address = address;
    }

    public static DefaultProfile getProfile(String accessKeyId, String secretKey, String address) {
        address = Optional.ofNullable(address).orElse(EnvEnum.FAT.name());
        if (null == profile) {
            synchronized (DefaultProfile.class){
                if (null == profile) {
                    if(HttpUtil.isHttp(address)||HttpUtil.isHttps(address)){
                        profile = new DefaultProfile(accessKeyId,secretKey,address);
                    }else{
                        Props props = new Props("open-sdk.properties");
                        profile = new DefaultProfile(accessKeyId,secretKey,props.get(address.toUpperCase()).toString());
                    }
                    mac = SecureUtil.hmacSha1(secretKey);
                }
            }
        }
        return profile;
    }


    @Override
    public String getAccessKeyId() {
        return profile.accessKeyId;
    }

    @Override
    public String getSecretKey() {
        return profile.secretKey;
    }

    @Override
    public String getAddress() {
        return profile.address;
    }

    @Override
    public HMac getMac() {
        return profile.mac;
    }
}
