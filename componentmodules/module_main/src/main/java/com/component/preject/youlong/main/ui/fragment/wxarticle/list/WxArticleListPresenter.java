package com.component.preject.youlong.main.ui.fragment.wxarticle.list;

import com.component.preject.youlong.main.bean.BaseResponse;
import com.component.preject.youlong.main.bean.WxArticleBean;
import com.component.preject.youlong.main.bean.WxArticleListBean;
import com.component.preject.youlong.main.rx.MainRxSchedulers;
import com.component.preject.youlong.rx.ApiException;
import com.component.preject.youlong.rx.HttpDisposableObserver;
import com.component.preject.youlong.main.bean.BaseResponse;
import com.component.preject.youlong.utils.LogUtils;

import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/7 11:43
 * @description: （添加一句描述）
 */
public class WxArticleListPresenter extends WxArticleListContract.Presenter {
    private final static String TAG=WxArticleListPresenter.class.getSimpleName();
    public WxArticleListPresenter() {
        mModel = new WxArticleListModel();
    }

    @Override
    void getWxList(int id, int page) {
        LogUtils.i(TAG,"getWxList=id="+id+" page="+page);
        HttpDisposableObserver<WxArticleListBean> disposableObserver = getWxList();
        mModel.getWxList(id, page)
                .compose(MainRxSchedulers.combine())
                .subscribe(disposableObserver);
        addSubscribe(disposableObserver);

    }

    @Override
    void getLoadMoreWxList(int id, int page) {
        HttpDisposableObserver<WxArticleListBean> disposableObserver = getMoreWxList();
        mModel.getWxList(id, page)
                .compose(MainRxSchedulers.combine())
                .subscribe(disposableObserver);
        addSubscribe(disposableObserver);
    }

    /**
     * 首次加载数据
     *
     * @return
     */
    private HttpDisposableObserver<WxArticleListBean> getWxList() {
        return new HttpDisposableObserver<WxArticleListBean>() {
            @Override
            public void onNext(WxArticleListBean bean) {
                LogUtils.i(TAG,"wxArticleListBeanBaseResponse="+bean);
                if (mView != null) {
                    mView.setLoadingSucceeded(bean);
                }
            }

            @Override
            public void onError(ApiException e) {
                LogUtils.i(TAG,"wxArticleListBeanBaseResponse=e="+e);
                if (mView != null) {
                    mView.setLoadingFail(e.getMsg());
                }

            }
        };
    }

    /**
     * 加载更多数据
     *
     * @return
     */
    private HttpDisposableObserver<WxArticleListBean> getMoreWxList() {
        return new HttpDisposableObserver<WxArticleListBean>() {
            @Override
            public void onNext(WxArticleListBean wxArticleListBeanBaseResponse) {
                if (mView != null) {
                    mView.setLoadMoreSucceeded(wxArticleListBeanBaseResponse);
                }
            }

            @Override
            public void onError(ApiException e) {
                if (mView != null) {
                    mView.setLoadMoreFail(e.getMsg());
                }

            }
        };
    }
}
