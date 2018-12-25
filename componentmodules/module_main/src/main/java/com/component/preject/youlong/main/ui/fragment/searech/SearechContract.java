package com.component.preject.youlong.main.ui.fragment.searech;

import android.content.Context;
import com.component.preject.youlong.base.mvp.BaseModel;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.BaseView;
import com.component.preject.youlong.main.bean.BaseResponse;
import com.component.preject.youlong.main.bean.HotKeyBean;
import io.reactivex.Observable;

import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/25 15:18
 * @description: （添加一句描述）
 */
public interface SearechContract {
    interface View extends BaseView {

        void getHotListOk(List<HotKeyBean> beanList);

        void getHotListErr(String err);

    }

    interface Model extends BaseModel {
        Observable<BaseResponse<List<HotKeyBean>>> getHotListResult();
        void saveHistory(Context context, List<String> historyList);
        void getHistory(Context context, List<String> historyList);
    }

    abstract class Presenter extends BasePresenter<View, Model> {

        abstract void getHotListResult();

        abstract void saveHistory(Context context, List<String> historyList);

        abstract void getHistoryList(Context context, List<String> historyList);

    }
}
