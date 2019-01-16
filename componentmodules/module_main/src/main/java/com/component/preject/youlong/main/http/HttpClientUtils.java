package com.component.preject.youlong.main.http;

import com.component.preject.youlong.main.common.Constant;

import static com.component.preject.youlong.main.http.ApiFactory.getInstance;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 16:31
 * @description: （添加一句描述）
 */
public class HttpClientUtils {
    public static WanAndroidApi getCommonHttp(){
        return (WanAndroidApi) ApiFactory.getInstance().create(WanAndroidApi.class, Constant.WanAndroidUrl.BASE_URL);
    }

    public static MovieApiService getMovieCommonHttp(){
        return (MovieApiService) ApiFactory.getInstance().create(MovieApiService.class, Constant.MovieUrl.BASE_URL);
    }
}
