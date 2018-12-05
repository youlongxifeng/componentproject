package com.component.preject.youlong.manage.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/5 17:30
 * @description: （线程工厂管理）
 */
public class ThreadFactoryManage {
    private static ThreadPoolProxy mNormalPool;    // 只需要初始化一次
    private static ThreadPoolProxy mDownLoadPool;    // 只需要初始化一次
    private static ExecutorService mCachedThreadPool;    // 只需要初始化一次

    /**
     * 普通的线程池
     */
    public static ThreadPoolProxy getNormaPool() {
        if (mNormalPool == null) {
            synchronized (ThreadFactoryManage.class) {
                if (mNormalPool == null) {
                    mNormalPool = new ThreadPoolProxy(5, 5, 3000);
                }
            }
        }
        return mNormalPool;
    }

    /**
     * 下载的线程池
     */
    public static ThreadPoolProxy getDownLoadPool() {
        if (mDownLoadPool == null) {
            synchronized (ThreadFactoryManage.class) {
                if (mDownLoadPool == null) {
                    mDownLoadPool = new ThreadPoolProxy(3, 3, 3000);
                }
            }
        }
        return mDownLoadPool;
    }

    /**
     * 可缓存线程池
     */
    public static ExecutorService getCachedThreadPool() {
        if (mCachedThreadPool == null) {
            synchronized (ThreadFactoryManage.class) {
                if (mCachedThreadPool == null) {
                    mCachedThreadPool = Executors.newCachedThreadPool();
                }
            }
        }
        return mCachedThreadPool;
    }
}
