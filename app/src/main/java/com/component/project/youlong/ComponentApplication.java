package com.component.project.youlong;


import com.component.preject.youlong.base.BaseApplication;
import com.component.preject.youlong.eventbus.EventBusHelper;
import com.component.project.youlong.bean.DaoMaster;
import com.component.project.youlong.bean.DaoSession;
import org.greenrobot.greendao.database.Database;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/4 14:54
 * @description: （添加一句描述）
 */
public class ComponentApplication extends BaseApplication {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        //崩溃日志记录初始化
       /* ACRA.init(this);
        ACRA.getErrorReporter().removeAllReportSenders();
        ACRA.getErrorReporter().setReportSender(new CrashReportSender());
        SmartEvents.post("");*/
        // EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        // regular SQLite database
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "fxhb.db");
        Database db = helper.getWritableDb();
        // encrypted SQLCipher database
        // note: you need to add SQLCipher to your dependencies, check the build.gradle file
        // DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db-encrypted");
        // Database db = helper.getEncryptedWritableDb("encryption-key");

        daoSession = new DaoMaster(db).newSession();
       // EventBusHelper.init();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
