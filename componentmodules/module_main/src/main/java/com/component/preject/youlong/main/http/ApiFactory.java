package com.component.preject.youlong.main.http;

import com.component.preject.youlong.main.common.Constant;
import retrofit2.Retrofit;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 16:33
 * @description: （添加一句描述）
 */
public class ApiFactory<T>  {
    private volatile static ApiFactory apiEngine;
    private Retrofit retrofit;

    private ApiFactory() {

       /* //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //缓存
        int size = 1024 * 1024 * 100;
        File cacheFile = new File(BaseApplication.getContext().getCacheDir(), "OkHttpCache");
        Cache cache = new Cache(cacheFile, size);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .cookieJar(new CookiesManager())//在OkHttpClient创建时，传入这个CookieJar的实现，就能完成对Cookie的自动管理了
                .addNetworkInterceptor(new NetworkInterceptor())// 将有网络拦截器当做网络拦截器添加
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(AppConfigInfo.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();*/

    }

    public static ApiFactory getInstance() {
        if (apiEngine == null) {
            synchronized (ApiFactory.class) {
                if (apiEngine == null) {
                    apiEngine = new ApiFactory();
                }
            }
        }
        return apiEngine;
    }

    /**
     * public ApiService getApiService() {
     * return retrofit.create(ApiService.class);
     * }
     */

    private    T commonApi;

    public T create(Class<T> clazz, String type) {
        switch (type) {
            case Constant.WanAndroidUrl.BASE_URL:
                if (commonApi == null) {
                    synchronized (ApiFactory.class) {
                        commonApi = HttpClient.getBuilder(type).build().create(clazz);
                    }
                }
                break;
        }
        return commonApi;
    }

}
