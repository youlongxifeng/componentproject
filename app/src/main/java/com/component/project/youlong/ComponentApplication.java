package com.component.project.youlong;

import android.app.Application;
import com.alibaba.android.arouter.launcher.ARouter;
import com.component.preject.youlong.base.BaseApplication;
import com.component.preject.youlong.utils.Utils;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/4 14:54
 * @description: （添加一句描述）
 */
public class ComponentApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //崩溃日志记录初始化
       /* ACRA.init(this);
        ACRA.getErrorReporter().removeAllReportSenders();
        ACRA.getErrorReporter().setReportSender(new CrashReportSender());
        SmartEvents.post("");*/
        // EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();

    }

}
