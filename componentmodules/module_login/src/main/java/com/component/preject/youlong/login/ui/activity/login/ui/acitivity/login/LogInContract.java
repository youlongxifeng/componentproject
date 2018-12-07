package com.component.preject.youlong.login.ui.activity.login.ui.acitivity.login;

import com.component.preject.youlong.base.mvp.BaseModel;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.BaseView;
import io.reactivex.Observable;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 11:34
 * @description: （添加一句描述）
 */
public interface LogInContract {
    interface View extends BaseView {

    }

    interface Model extends BaseModel {

    }

    abstract class Presenter extends BasePresenter<View,Model> {

    }
}
