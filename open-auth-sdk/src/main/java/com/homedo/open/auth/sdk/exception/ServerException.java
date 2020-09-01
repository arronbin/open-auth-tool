package com.homedo.open.auth.sdk.exception;

import com.homedo.open.auth.sdk.enums.ErrorTypeEnum;

/**
 * @Auther: eric
 * @Date: 2020/8/26
 * @Description:
 */
public class ServerException extends ClientException{
    private static final long serialVersionUID = -7342153650798165336L;

    public ServerException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
        this.setErrorType(ErrorTypeEnum.Server);
    }

    public ServerException(String errCode, String errMsg, String requestId) {
        this(errCode, errMsg);
        this.setRequestId(requestId);
    }
}
