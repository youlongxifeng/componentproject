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
        if (Utils.isAppDebug()) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            //开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！
            //线上版本需要关闭,否则有安全风险)
            ARouter.openLog();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);
        //崩溃日志记录初始化
       /* ACRA.init(this);
        ACRA.getErrorReporter().removeAllReportSenders();
        ACRA.getErrorReporter().setReportSender(new CrashReportSender());
        SmartEvents.post("");*/
        // EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();

    }

}
