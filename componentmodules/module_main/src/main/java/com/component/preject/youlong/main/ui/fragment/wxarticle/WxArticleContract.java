package com.component.preject.youlong.main.ui.fragment.wxarticle;

import com.component.preject.youlong.base.mvp.BaseModel;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.BaseView;
import com.component.preject.youlong.main.bean.BaseResponse;
import com.component.preject.youlong.main.bean.WxArticleBean;
import io.reactivex.Observable;

import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 16:28
 * @description: （添加一句描述）
 */
public interface WxArticleContract {
    interface View extends BaseView {
        void setLoadingSucceeded(List<WxArticleBean> wxArticleBeanList);
        void setLoadingFail(String fail);
    }

    interface Model extends BaseModel {
        Observable<BaseResponse<List<WxArticleBean>>>wxChapters();

    }

    abstract class Presenter extends BasePresenter<View,Model> {
        abstract void getWxTabs();

    }
}
