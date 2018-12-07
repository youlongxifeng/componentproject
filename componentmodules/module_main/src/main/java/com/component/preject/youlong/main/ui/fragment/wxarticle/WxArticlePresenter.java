package com.component.preject.youlong.main.ui.fragment.wxarticle;

import com.component.preject.youlong.main.bean.WxArticleBean;
import com.component.preject.youlong.main.rx.MainRxSchedulers;
import com.component.preject.youlong.rx.ApiException;
import com.component.preject.youlong.rx.HttpDisposableObserver;
import com.component.preject.youlong.utils.LogUtils;
import io.reactivex.observers.DisposableObserver;

import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 17:04
 * @description: （添加一句描述）
 */
public class WxArticlePresenter extends WxArticleContract.Presenter {
    private static final String TAG = WxArticlePresenter.class.getSimpleName();

    public WxArticlePresenter() {
        mModel = new WxArticleModel();
    }

    @Override
    void getWxTabs() {
        DisposableObserver<List<WxArticleBean>> disposableObserver = getwxChapters();
        mModel.wxChapters()
                .compose(MainRxSchedulers.combine())
                .subscribe(disposableObserver);
    }

    private HttpDisposableObserver<List<WxArticleBean>> getwxChapters() {
        return new HttpDisposableObserver<List<WxArticleBean>>() {
            @Override
            public void onError(ApiException e) {
                LogUtils.i(TAG, "onError e==" + e);
                if(mView!=null){
                    mView.setLoadingFail(e.getMsg());
                }
            }

            @Override
            public void onNext(List<WxArticleBean> wxArticleBeans) {
                if(mView!=null){
                    mView.setLoadingSucceeded(wxArticleBeans);
                }
                LogUtils.i(TAG, "onNext wxArticleBeans==" + wxArticleBeans);
            }
        };
    }
}
