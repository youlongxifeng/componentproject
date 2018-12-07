package com.component.preject.youlong.main.ui.fragment.wxarticle.list;

import com.component.preject.youlong.base.mvp.BaseModel;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.BaseView;
import com.component.preject.youlong.main.bean.BaseResponse;
import com.component.preject.youlong.main.bean.WxArticleBean;
import com.component.preject.youlong.main.bean.WxArticleListBean;
import com.component.preject.youlong.main.ui.fragment.wxarticle.WxArticleContract;
import io.reactivex.Observable;
import retrofit2.http.Path;

import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/7 11:21
 * @description: （添加一句描述）
 */
public interface WxArticleListContract {
    interface View extends BaseView {
        void setLoadingSucceeded(WxArticleListBean wxArticleListBean);
        void setLoadingFail(String fail);
        void setLoadMoreSucceeded(WxArticleListBean wxArticleListBean);
        void setLoadMoreFail(String fail);
    }

    interface Model extends BaseModel {
        Observable<BaseResponse<WxArticleListBean>> getWxList(int id, int page);

    }

    abstract class Presenter extends BasePresenter< View, Model> {
        abstract void getWxList(int id, int page);//
        abstract void getLoadMoreWxList(int id, int page);
    }
}
