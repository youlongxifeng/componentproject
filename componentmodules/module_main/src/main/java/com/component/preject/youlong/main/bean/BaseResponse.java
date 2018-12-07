package com.component.preject.youlong.main.bean;

import java.io.Serializable;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 17:00
 * @description: （添加一句描述）
 */
public class BaseResponse<T> implements Serializable {
    public final static int CODE_SUCCESS0 = 0;     // 服务端返回码，成功（兼容以前 api）
    public final static int CODE_SUCCESS = 200;     // 服务端返回码，成功
    private int errorCode;
    private String errorMsg;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
