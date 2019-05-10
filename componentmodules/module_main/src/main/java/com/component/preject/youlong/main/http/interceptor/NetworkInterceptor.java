package com.component.preject.youlong.main.http.interceptor;

import com.component.preject.youlong.base.BaseApplication;
import com.component.preject.youlong.utils.LogUtils;
import com.component.preject.youlong.utils.NetWorkUtil;
import com.component.preject.youlong.utils.SystemDeviceParameter;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 16:34
 * @description: （添加一句描述）
 */
public class NetworkInterceptor implements Interceptor {
    private final static String TAG = NetworkInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request requestBuilder = chain.request();
        Request.Builder builder = requestBuilder.newBuilder();
        boolean isConnected = NetWorkUtil.isConnected(BaseApplication.getContext());
        LogUtils.i(TAG, "isConnected=" + isConnected);
        //无网络时强制使用缓存
        if (!isConnected) {
            builder.cacheControl(CacheControl.FORCE_CACHE)
                    .build();

        }
        String method = requestBuilder.method();
        if ("POST".equalsIgnoreCase(method)) {
            LogUtils.i(TAG, "POST 请求方式  url=" + requestBuilder.url());
        } else if ("GET".equalsIgnoreCase(method)) {
            LogUtils.i(TAG, "GET 请求方式  url=" + requestBuilder.url());
        }
        Request request = null;
        //无网络时强制使用缓存
        if (!isConnected) {
            builder.cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }else {
           request = builder.build();

        }
        LogUtils.i(TAG,  "builder===" + request.headers().size() + "  ==" + request.headers().toString());
        Response response = chain.proceed(request);
       /* String cookie = response.header("Set-Cookie");
        if (cookie != null) {
            SharedPrefercesUtils.saveCookiePreference(BaseApplication.getContext().getBaseContext(), cookie);
        }*/

        if (isConnected) {
            // 有网络时，设置超时为0
            int maxStale = 600;
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxStale)
                    // .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build();
        } else {
            // 无网络时，设置超时为3周
            int maxStale = 60 * 60 * 24 * 21;
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    // .removeHeader("Pragma")
                    .build();
        }
        LogUtils.e(TAG, "isSuccessful==="+ response.isSuccessful()+"  response="+response);
        if (response.isSuccessful()) {
            LogUtils.e(TAG, "networkResponse==="+ response.networkResponse());
            LogUtils.e(TAG, "cacheResponse==="+ response.cacheResponse());
            if (response.networkResponse() != null) {
                LogUtils.e(TAG, "network==="+ response.body().string().length() + "");
            } else if (response.cacheResponse() != null) {
                if (isConnected) {
                    LogUtils.e(TAG, "cache=="+ response.body().string().length() + "");
                } else {
                    LogUtils.e(TAG, "cache(no network)==="+ response.body().string().length() + "");
                }
            }
        }

        return response;
    }
}


