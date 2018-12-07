package com.component.preject.youlong.main.ui.fragment.wxarticle;

import com.component.preject.youlong.main.bean.BaseResponse;
import com.component.preject.youlong.main.bean.WxArticleBean;
import com.component.preject.youlong.main.http.HttpClientUtils;
import io.reactivex.Observable;

import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 17:03
 * @description: （添加一句描述）
 */
public class WxArticleModel implements WxArticleContract.Model {
    @Override
    public Observable<BaseResponse<List<WxArticleBean>>> wxChapters() {
        return HttpClientUtils.getCommonHttp().getWxChapters();
    }
}
