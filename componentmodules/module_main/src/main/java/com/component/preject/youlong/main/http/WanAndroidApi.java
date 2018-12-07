package com.component.preject.youlong.main.http;

import com.component.preject.youlong.main.bean.BaseResponse;
import com.component.preject.youlong.main.bean.WxArticleBean;
import io.reactivex.Observable;
import retrofit2.http.GET;

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
}
