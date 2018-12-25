package com.component.preject.youlong.main.http;

import com.component.preject.youlong.main.bean.*;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 16:32
 * @description: （添加一句描述）
 */
public interface WanAndroidApi {

    /**
     * 公众号tab
     */
    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<WxArticleBean>>> getWxChapters();


    /**
     * 公众号历史数据
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponse<WxArticleListBean>> getWxList(@Path("id") int id , @Path("page") int page );


    /**
     * 主页
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<HomePageArticleBean>> getArticleList(@Path("page") int num);

    /**
     * 主页banner
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BenarBean>>>getBannerList();

    /**
     * 获取 搜索热词
     * @return
     */
    @GET("/hotkey/json")
    Observable<BaseResponse<List<HotKeyBean>>> getHotListResult();
}
