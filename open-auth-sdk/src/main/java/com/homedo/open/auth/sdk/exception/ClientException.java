package com.homedo.open.auth.sdk.exception;

import com.homedo.open.auth.sdk.enums.ErrorTypeEnum;
import lombok.Data;

/**
 * @Auther: eric
 * @Date: 2020/8/26
 * @Description:
 */
@Data
public class ClientException extends Exception{

    private static final long serialVersionUID = 534548624110290578L;

    private String requestId;

    private String errCode;

    private String errMsg;

    private ErrorTypeEnum errorType;

    private String errorDescription;

    public ClientException(String errorCode, String errorMessage, String requestId, String errorDescription) {
        this(errorCode, errorMessage);
        this.setErrorDescription(errorDescription);
        this.setRequestId(requestId);
    }

    public ClientException(String errCode, String errMsg, String requestId) {
        this(errCode, errMsg);
        this.requestId = requestId;
        this.setErrorType(ErrorTypeEnum.Client);
    }

    public ClientException(String errCode, String errMsg, Throwable cause) {
        super(errCode + " : " + errMsg, cause);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.setErrorType(ErrorTypeEnum.Client);
    }

    public ClientException(String errCode, String errMsg) {
        super(errCode + " : " + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.setErrorType(ErrorTypeEnum.Client);
    }

    public ClientException(String message) {
        super(message);
        this.setErrorType(ErrorTypeEnum.Client);
    }

    public ClientException(Throwable cause) {
        super(cause);
        this.setErrorType(ErrorTypeEnum.Client);
    }

}
