package com.component.preject.youlong.base;

import android.app.Application;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/4 15:14
 * @description: （添加一句描述）
 */
public class BaseApplication extends Application {
    private static BaseApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static BaseApplication getContext() {
        return mContext;
    }
}
