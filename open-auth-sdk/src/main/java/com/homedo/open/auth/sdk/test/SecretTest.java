package com.homedo.open.auth.sdk.test;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: eric
 * @Date: 2020/8/31
 * @Description: 加密测试
 */
public class SecretTest {

    public static void main(String[] args) {
        String secretKey = "zgJ2f-IsXunxUsaEVF1XP_oSDfs2AE";
        String concatStr = "itisademo";
        HMac mac = SecureUtil.hmacSha1(secretKey);
        Stopwatch stopwatch = Stopwatch.createStarted();
        String secret = Base64.encode(mac.digest(concatStr));
        System.out.println(StrUtil.format("{}-->{}",stopwatch.elapsed(TimeUnit.MILLISECONDS),secret));
    }

}
