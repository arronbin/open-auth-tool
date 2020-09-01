package com.homedo.open.auth.sdk;

import com.homedo.open.auth.sdk.model.basic.BaseModel;

/**
 * @Auther: eric
 * @Date: 2020/8/26
 * @Description:
 */
public abstract class AcsRequest<T extends AcsResponse> {

    abstract BaseModel buildBaseModel(AcsRequest request);

}
