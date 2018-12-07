package com.component.preject.youlong.rx;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 17:11
 * @description: （添加一句描述）
 */
public interface ServerConstant {
    int CODE_SUCCESS0 = 0;     // 服务端返回码，成功（兼容以前 api）
    int CODE_SUCCESS = 200;     // 服务端返回码，成功
    int  CODE_PAY_SUCCESS = 2017188;     //  资源支付成功

    int CODE_TOKEN_INVALID = 4019; // token 失效

    int ERROR_UNKNOWN = 1000;    // 未知错误
    int ERROR_PARSE = 1001;    // 解析错误
    int ERROR_NETWORK = 1002;    // 网络错误
    int ERROR_HTTP = 1003;    // 协议出错
}
