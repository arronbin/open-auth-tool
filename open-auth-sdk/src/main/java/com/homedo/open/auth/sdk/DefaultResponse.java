package com.homedo.open.auth.sdk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: eric
 * @Date: 2020/8/26
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponse<T> extends AcsResponse{

    private String bizCode;

    private T data;

    private String respCode;

    private String respDesc;
}
