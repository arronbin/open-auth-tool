package com.homedo.open.auth.sdk.convert;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.homedo.open.auth.sdk.constants.OpenAuthSdkConstant;
import com.homedo.open.auth.sdk.model.basic.OpenAuthSdkMapModel;
import com.homedo.open.auth.sdk.model.gateway.OpenAuthSdkParamModel;
import lombok.experimental.UtilityClass;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: eric
 * @Date: 2020/8/25
 * @Description: 参数转换类
 */
@UtilityClass
public class OpenAuthSdkParamConvert {

    /**
     * 网关鉴权参数转换key-value形式
     * @param model 网关鉴权参数实体类
     * @return List
     */
    public List<OpenAuthSdkMapModel> convertParamListIfNeed(OpenAuthSdkParamModel model){
        List<String>ignoreList = Lists.newArrayList("resource","bodyContent","httpMethod","customHeaders","timeout");
        return ReflectUtil.getFieldMap(OpenAuthSdkParamModel.class)
                .entrySet()
                .stream()
                .filter(f->!ignoreList.contains(f.getKey()))
                .map(m->{
                    String value = StrUtil.str(ReflectUtil.getFieldValue(model,m.getKey()), OpenAuthSdkConstant.CHARSET_UTF8);
                    return new OpenAuthSdkMapModel(StrUtil.str(m.getKey(), OpenAuthSdkConstant.CHARSET_UTF8),StrUtil.isNotBlank(value)?value:"");
                })
                .collect(Collectors.toList())
                .stream().sorted(Comparator.comparing(OpenAuthSdkMapModel::getKey)).collect(Collectors.toList());
    }

    /**
     * 包装入参
     * @param model
     * @return
     */
    public void wrapOpenAuthSdkParamModelIfNeed(OpenAuthSdkParamModel model){
        DateFormat DATE_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
        String gmt = DATE_FORMAT.format(new Date());
        model.setContentType(OpenAuthSdkConstant.contentType);
        model.setDateGMTStr(gmt);
        model.setContentMd5("");
        Object bodyContent = model.getBodyContent();
        if(StrUtil.equals(model.getHttpMethod(), Method.POST.name())&&ObjectUtil.isNotEmpty(bodyContent)){
            model.setContentMd5(SecureUtil.md5().digestHex(JSON.toJSONString(bodyContent).getBytes()));
        }
    }

    /**
     * 自动组装拼接字符串
     * @param model 加密参数封装类
     * @return
     */
    public String autoConcatStr(OpenAuthSdkParamModel model){
        List<OpenAuthSdkMapModel>models = convertParamListIfNeed(model);
        return StrUtil.format("{}{}{}{}",
                model.getHttpMethod(),OpenAuthSdkConstant.SEPARATOR_NEWLINE,wrapListToStr(models),model.getResource());
    }

    /**
     * 拼接字符串
     * @param model 加密参数封装类
     * @return
     */
    public String concatStr(OpenAuthSdkParamModel model){
        List<String>strs = Lists.newArrayList(model.getContentMd5(),model.getContentType(),model.getDateGMTStr(),"");
        return StrUtil.format("{}{}{}{}",
                model.getHttpMethod(),OpenAuthSdkConstant.SEPARATOR_NEWLINE,
                StrUtil.join(OpenAuthSdkConstant.SEPARATOR_NEWLINE,strs)
                ,model.getResource());
    }

    /**
     * 集合转换字符串，按"\n"分隔
     * @param models
     * @return
     */
    public String wrapListToStr(List<OpenAuthSdkMapModel>models){
        return models.stream().map(OpenAuthSdkMapModel::getValue).collect(Collectors.joining(OpenAuthSdkConstant.SEPARATOR_NEWLINE));
    }

}
