package com.homedo.open.auth.sdk;

import com.homedo.open.auth.sdk.exception.ClientException;
import com.homedo.open.auth.sdk.exception.ServerException;
/**
 * @Auther: eric
 * @Date: 2020/8/26
 * @Description: 客户端配置
 */
public interface IAcsClient {

    /**
     *  GET请求服务器
     * @param request
     * @param <T>
     * @return
     * @throws ServerException 服务端异常
     * @throws ClientException 客户端异常
     */
    <T extends AcsResponse> T getAcsResponse(AcsRequest request)throws ServerException, ClientException;

    /**
     * POST请求服务器
     * @param request
     * @param <T>
     * @return
     * @throws ServerException 服务端异常
     * @throws ClientException 客户端异常
     */
    <T extends AcsResponse> T postAcsResponse(AcsRequest request)throws ServerException, ClientException;

    /**
     *  GET请求服务器
     * @param request
     * @return
     * @throws ServerException 服务端异常
     * @throws ClientException 客户端异常
     */
    <T extends AcsResponse> T getAcsResponse(AcsRequest request,Class clz)throws ServerException, ClientException;

    /**
     * POST请求服务器
     * @param request
     * @param <T>
     * @return
     * @throws ServerException 服务端异常
     * @throws ClientException 客户端异常
     */
    <T extends AcsResponse> T postAcsResponse(AcsRequest request,Class clz)throws ServerException, ClientException;

}
