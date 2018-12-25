package com.component.preject.youlong.main.ui.fragment.home;

import com.component.preject.youlong.main.bean.BaseResponse;
import com.component.preject.youlong.main.bean.BenarBean;
import com.component.preject.youlong.main.bean.HomePageArticleBean;
import com.component.preject.youlong.main.http.HttpClientUtils;
import io.reactivex.Observable;

import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/25 10:43
 * @description: （添加一句描述）
 */
public class HomeModel implements HomeContract.Model {
     @Override
    public Observable<BaseResponse<HomePageArticleBean>> getArticleList(int page) {
        return HttpClientUtils.getCommonHttp().getArticleList(page);
    }

    @Override
    public Observable<BaseResponse<List<BenarBean>>> getBannerList() {
        return HttpClientUtils.getCommonHttp().getBannerList();
    }
}
