package com.component.preject.youlong.main.ui.fragment.home;

import com.component.preject.youlong.main.bean.BenarBean;
import com.component.preject.youlong.main.bean.HomePageArticleBean;
import com.component.preject.youlong.main.rx.MainRxSchedulers;
import com.component.preject.youlong.rx.ApiException;
import com.component.preject.youlong.rx.HttpDisposableObserver;
import com.component.preject.youlong.rx.RxSchedulers;

import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/25 10:40
 * @description: （添加一句描述）
 */
public class HomePagePresenter extends HomeContract.Presenter {
    private int currentPage;
    private boolean isRefresh = true;

    public HomePagePresenter() {
        mModel = new HomeModel();
    }

    @Override
    void autoRefresh() {
        isRefresh = true;
        currentPage = 0;
        getBanner();
        getHomepageListData(currentPage);
    }

    @Override
    void loadMore() {
        isRefresh = false;
        currentPage++;
        getHomepageListData(currentPage);
    }

    @Override
    void getBanner() {
        HttpDisposableObserver<List<BenarBean>> disposableObserver = getBannerList();
        mModel.getBannerList()
                .compose(MainRxSchedulers.combine())
                .subscribe(disposableObserver);
        addSubscribe(disposableObserver);
    }

    /**
     * 获取首页 信息
     *
     * @param page
     */
    @Override
    void getHomepageListData(int page) {
        HttpDisposableObserver<HomePageArticleBean> disposableObserver = getArticleList(page,isRefresh);
        mModel.getArticleList(page)
                .compose(MainRxSchedulers.combine())
                .subscribe(disposableObserver);
        addSubscribe(disposableObserver);
    }

    @Override
    void loginUser(String username, String password) {

    }

    private HttpDisposableObserver<HomePageArticleBean> getArticleList(int page,final boolean isRefresh) {
        return new HttpDisposableObserver<HomePageArticleBean>() {
            @Override
            public void onError(ApiException e) {
                if(mView!=null){
                    mView.getHomepageListErr(e.getMsg(),isRefresh);
                }
            }

            @Override
            public void onNext(HomePageArticleBean homePageArticleBean) {
                if(mView!=null){
                    mView.getHomepageListOk(homePageArticleBean,isRefresh);
                }
            }
        };
    }

    private HttpDisposableObserver<List<BenarBean>> getBannerList() {
        return new HttpDisposableObserver<List<BenarBean>>() {
            @Override
            public void onNext(List<BenarBean> benarBeans) {
                if (mView != null) {
                    mView.getBannerOk(benarBeans);
                }
            }

            @Override
            public void onError(ApiException e) {
                if (mView != null) {
                    mView.getBannerErr(e.getMessage());
                }
            }
        };
    }

    ;
}
