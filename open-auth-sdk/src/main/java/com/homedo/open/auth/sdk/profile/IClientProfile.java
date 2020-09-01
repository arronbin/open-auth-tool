package com.homedo.open.auth.sdk.profile;

import cn.hutool.crypto.digest.HMac;

/**
 * @Auther: eric
 * @Date: 2020/8/26
 * @Description:环境设置
 */
public interface IClientProfile {

   String getAccessKeyId();

   String getSecretKey();

   String getAddress();

   HMac getMac();
}
