package com.component.preject.youlong.eventbus;

import android.os.Handler;
import android.os.Looper;
import org.greenrobot.eventbus.EventBus;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/7 16:06
 * @description: （添加一句描述）
 */
public class SmartEvents {
    private final static SmartEvents mInstance = new SmartEvents();

    public static SmartEvents instance() {
        return mInstance;
    }

    public void post(Object obj) {
        post(obj, 0l);
    }

    public static void post(final Object obj, long delay) {
        if (delay > 0) {
            mInstance.mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mInstance.mEventsBus.post(obj);
                }
            }, delay);
        } else {
            mInstance.mEventsBus.post(obj);
        }
    }

    public static void register(Object obj) {
        mInstance.mEventsBus.register(obj);
    }

    public static void unregister(Object obj) {
        mInstance.mEventsBus.unregister(obj);
    }

    public static boolean isRegistered(Object obj) {
        return mInstance.mEventsBus.isRegistered(obj);
    }

    private final Handler mHandler;
    private final EventBus mEventsBus;

    private SmartEvents() {
        //添加索引,添加索引后只有执行相关索引MyEventBusIndex才会有，做记录
        // mEventsBus = EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        mEventsBus = EventBus.getDefault();
        mHandler = new Handler(Looper.getMainLooper());
    }
}