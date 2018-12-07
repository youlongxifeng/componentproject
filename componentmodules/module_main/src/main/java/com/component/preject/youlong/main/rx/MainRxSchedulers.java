package com.component.preject.youlong.main.rx;

import com.component.preject.youlong.main.bean.BaseResponse;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 17:09
 * @description: （添加一句描述）
 */
public class MainRxSchedulers {
    public static <T> ObservableTransformer<BaseResponse<T>, T> combine() {
        return new ObservableTransformer<BaseResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .map(new MapFunction<T>())
                        .onErrorResumeNext(new ErrorObservableSource<T>())
                        .observeOn(AndroidSchedulers.mainThread());

            }
        };
    }
}
