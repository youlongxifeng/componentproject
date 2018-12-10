package com.component.preject.youlong.base;

import android.app.Application;
import com.alibaba.android.arouter.launcher.ARouter;
import com.component.preject.youlong.eventbus.SmartEvents;
import com.component.preject.youlong.utils.LogUtils;
import com.component.preject.youlong.utils.Utils;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/4 15:14
 * @description: （添加一句描述）
 */
public class BaseApplication extends Application {
    private final static String TAG=BaseApplication.class.getSimpleName();
    private static BaseApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Utils.init(this);
        LogUtils.init(null,true,true);
        if (Utils.isAppDebug()) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            //开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！
            //线上版本需要关闭,否则有安全风险)
            ARouter.openLog();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);




    }

    public static BaseApplication getContext() {
        return mContext;
    }



}
