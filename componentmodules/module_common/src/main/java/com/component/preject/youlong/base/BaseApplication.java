package com.component.preject.youlong.base;

import android.app.Application;
import com.component.preject.youlong.utils.LogUtils;
import com.component.preject.youlong.utils.Utils;

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
        Utils.init(this);
        LogUtils.init(null,true,true);
    }

    public static BaseApplication getContext() {
        return mContext;
    }
}
