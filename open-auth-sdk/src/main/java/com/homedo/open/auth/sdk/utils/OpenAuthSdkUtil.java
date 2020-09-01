package com.homedo.open.auth.sdk.utils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.HMac;
import com.google.common.collect.Maps;
import com.homedo.open.auth.sdk.constants.OpenAuthSdkConstant;
import com.homedo.open.auth.sdk.convert.OpenAuthSdkParamConvert;
import com.homedo.open.auth.sdk.model.business.BusinessDataModel;
import com.homedo.open.auth.sdk.model.gateway.OpenAuthSdkParamModel;
import com.homedo.open.auth.sdk.profile.DefaultProfile;
import lombok.experimental.UtilityClass;
import java.util.Map;
/**
 * @Auther: eric
 * @Date: 2020/8/25
 * @Description:开放平台加密，解密，验签,生成请求头工具类
 */
@UtilityClass
public class OpenAuthSdkUtil {
    /**
     * 获取请求头信息
     * @param model
     * @param profile
     * @return
     */
    public Map<String,String> buildHttpHeadersMap(BusinessDataModel model,DefaultProfile profile){
        OpenAuthSdkParamModel sdkParamModel = new OpenAuthSdkParamModel();
        BeanUtil.copyProperties(model,sdkParamModel);
        OpenAuthSdkParamConvert.wrapOpenAuthSdkParamModelIfNeed(sdkParamModel);
        String signature = OpenAuthSdkUtil.signature(OpenAuthSdkParamConvert.concatStr(sdkParamModel),profile.getMac());
        String authorization = OpenAuthSdkUtil.buildAuthorization(profile.getAccessKeyId(),signature);
        Map<String,String>map = Maps.newHashMap();
        map.put("Authorization", authorization);
        map.put("Date", sdkParamModel.getDateGMTStr());
        map.put("Content-Type", OpenAuthSdkConstant.contentType);
        if(StrUtil.isNotBlank(sdkParamModel.getContentMd5())){
            map.put("Content-md5",sdkParamModel.getContentMd5());
        }
        map.put("CanonicalizedResource", sdkParamModel.getResource());
        return map;
    }

    /**
     * 签名
     * @param concatStr 拼接字符串
     * @param mac 密钥
     * @return
     */
    public String signature(String concatStr, HMac mac){
        return Base64.encode(mac.digest(concatStr));
    }

    /**
     * 获取用户凭证
     * @param keyId
     * @param signature
     * @return
     */
    public String buildAuthorization(String keyId,String signature){
        return StrUtil.format("{}{}{}{}",
                OpenAuthSdkConstant.ATFORM_NAME,keyId,OpenAuthSdkConstant.SEPARATOR_COLON,signature);
    }
}
