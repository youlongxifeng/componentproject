package com.component.preject.youlong.main.ui.fragment.home;

import com.component.preject.youlong.base.mvp.BaseModel;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.BaseView;
import com.component.preject.youlong.main.bean.*;
import io.reactivex.Observable;

import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/25 10:36
 * @description: （添加一句描述）
 */
public interface HomeContract {


    public interface View extends BaseView {

        void getHomepageListOk(HomePageArticleBean dataBean, boolean isRefresh);

        void getHomepageListErr(String info, boolean isRefresh);

        void getBannerOk(List<BenarBean> bannerBean);

        void getBannerErr(String info);

        void loginOk(UserInfo userInfo);

        void loginErr(String err);
    }

    interface Model extends BaseModel {
        Observable<BaseResponse<HomePageArticleBean>> getArticleList(int page);
        Observable<BaseResponse<List<BenarBean>>> getBannerList();

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        /**
         * 刷新 列表
         */
        abstract void autoRefresh();

        /**
         * 加載更多
         */
        abstract void loadMore();

        /**
         * 获取 轮播信息
         */
        abstract void getBanner();

        /**
         * 获取 首页 页数数据
         *
         * @param page
         */
        abstract void getHomepageListData(int page);

        /**
         * 帐号 登录
         */
        abstract void loginUser(String username, String password);
    }

}
