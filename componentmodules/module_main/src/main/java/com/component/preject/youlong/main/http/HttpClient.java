package com.component.preject.youlong.main.http;

import com.component.preject.youlong.base.BaseApplication;
import com.component.preject.youlong.http.cookies.CookiesManager;
import com.component.preject.youlong.http.interceptor.HttpLoggingInterceptor;
import com.component.preject.youlong.main.http.interceptor.NetworkInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 16:41
 * @description: （添加一句描述）
 */
public class HttpClient {

    public static Retrofit.Builder getBuilder(String apiUrl  )   {
        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //缓存
        int size = 1024 * 1024 * 100;
        File cacheFile = new File(BaseApplication.getContext().getCacheDir(), "OkHttpCache");
        Cache cache = new Cache(cacheFile, size);

        OkHttpClient mHttpClient = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .cookieJar(new CookiesManager())//在OkHttpClient创建时，传入这个CookieJar的实现，就能完成对Cookie的自动管理了
                .addNetworkInterceptor(new NetworkInterceptor())// 将有网络拦截器当做网络拦截器添加
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(mHttpClient);
        builder.baseUrl(apiUrl);//设置远程地址
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return builder;
    }
}
