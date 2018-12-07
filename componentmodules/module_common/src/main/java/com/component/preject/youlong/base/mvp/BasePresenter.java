package com.component.preject.youlong.base.mvp;

import android.util.Log;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 11:28
 * @description: （添加一句描述）
 */

public class BasePresenter<V extends BaseView,M extends BaseModel> {

    protected V mView;
     protected M mModel;


    public void attachView(BaseView view) {
        this.mView = (V) view;
        Log.i("TAG", "=====attachView=" + view.getClass().getName());
    }

    public void detachView() {
        Log.i("TAG", "=====detachView=");
        if (mView != null) {
            mView = null;
        }
    }

    private CompositeDisposable mCompositeSubscription;// 每当我们得到一个Disposable时就调用CompositeDisposable.add()将它添加到容器中,
    // 在退出的时候, 调用CompositeDisposable.clear() 即可切断所有的水管.

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeDisposable();
        }
        mCompositeSubscription.add(subscription);
    }

    /**
     * 在退出的时候, 调用CompositeDisposable.clear() 即可切断所有的水管.
     */
    public void unSubscribe() {
        if (mView != null) {
            mView = null;
        }
        if (mCompositeSubscription != null && mCompositeSubscription.isDisposed()) {
            mCompositeSubscription.dispose();
            mCompositeSubscription.clear();
        }
    }

}
