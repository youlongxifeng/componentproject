package com.component.preject.youlong.main.ui.fragment.searech;

import android.content.Context;
import com.component.preject.youlong.main.bean.HotKeyBean;
import com.component.preject.youlong.main.rx.MainRxSchedulers;
import com.component.preject.youlong.rx.ApiException;
import com.component.preject.youlong.rx.HttpDisposableObserver;

import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/25 15:23
 * @description: （添加一句描述）
 */
public class SearechPresenter extends SearechContract.Presenter {

    public SearechPresenter() {
        mModel = new SearechModel();
    }

    @Override
    void getHotListResult() {
        HttpDisposableObserver<List<HotKeyBean>> disposableObserver = getHotListResultObserver();
        mModel.getHotListResult()
                .compose(MainRxSchedulers.combine())
                .subscribe(disposableObserver);
        addSubscribe(disposableObserver);

    }

    @Override
    void saveHistory(Context context, List<String> historyList) {
        mModel.saveHistory(context, historyList);
    }

    @Override
    void getHistoryList(Context context, List<String> historyList) {
        mModel.getHistory(context, historyList);
    }

    private HttpDisposableObserver<List<HotKeyBean>> getHotListResultObserver() {
        return new HttpDisposableObserver<List<HotKeyBean>>() {
            @Override
            public void onError(ApiException e) {
                if (mView != null) {
                    mView.getHotListErr(e.getMsg());
                }
            }

            @Override
            public void onNext(List<HotKeyBean> hotKeyBeans) {
                if (mView != null) {
                    mView.getHotListOk(hotKeyBeans);
                }
            }
        };
    }
}
