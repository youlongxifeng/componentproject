package com.component.preject.youlong.main.http;

import com.component.preject.youlong.main.bean.BaseResponse;
import com.component.preject.youlong.main.bean.WxArticleBean;
import com.component.preject.youlong.main.bean.WxArticleListBean;
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
}
