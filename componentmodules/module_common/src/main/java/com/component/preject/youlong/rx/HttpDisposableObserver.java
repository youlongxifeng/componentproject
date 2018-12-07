package com.component.preject.youlong.rx;

import io.reactivex.observers.DisposableObserver;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 17:16
 * @description: （重写观察者）
 */
public  abstract class HttpDisposableObserver<T>  extends DisposableObserver<T> {
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(new ApiException(e));
        }
    }

    @Override
    public void onComplete() {
    }

    public abstract void onError(ApiException e);
}
