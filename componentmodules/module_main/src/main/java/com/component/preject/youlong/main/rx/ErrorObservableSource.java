package com.component.preject.youlong.main.rx;

import com.component.preject.youlong.rx.ApiException;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 17:10
 * @description: （添加一句描述）
 */
public class ErrorObservableSource<T> implements Function<Throwable, ObservableSource<? extends T>> {
    @Override
    public ObservableSource<? extends T>  apply(Throwable throwable) throws Exception {
        return Observable.error(ApiException.handleException(throwable));
    }
}