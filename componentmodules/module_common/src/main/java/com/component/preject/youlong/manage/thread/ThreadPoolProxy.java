package com.component.preject.youlong.manage.thread;

import com.component.preject.youlong.utils.LogUtils;

import java.util.concurrent.*;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/5 17:30
 * @description: （线程池管理类）
 */
public class ThreadPoolProxy {
    ThreadPoolExecutor mExecutor;
    int mCorePoolSize;
    int mMaximumPoolSize;
    long mKeepAliveTime;

    public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        mCorePoolSize = corePoolSize;
        mMaximumPoolSize = maximumPoolSize;
        mKeepAliveTime = keepAliveTime;
    }
    /**
     *  初始化ThreadPoolExecutor
     */
    private void initTThreadPoolExecutor() {// 双重间检查加锁
        if (mExecutor == null) {
            synchronized (ThreadPoolProxy.class) {
                if (mExecutor == null) {
                    TimeUnit unit = TimeUnit.MICROSECONDS;
                    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
                    java.util.concurrent.ThreadFactory threadFactory = Executors
                            .defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

                    mExecutor = new ThreadPoolExecutor(mCorePoolSize,// 核心线程数
                            mMaximumPoolSize,// 最大线程数
                            mKeepAliveTime, // 保持时间
                            unit, // 保持时间的单位
                            workQueue, // 工作队列
                            threadFactory, // 线程工厂
                            handler// 异常捕获器
                    );
                }
            }
        }
    }

    /**
     * 执行任务
     */
    public void execute(Runnable task) {
        initTThreadPoolExecutor();
        mExecutor.execute(task);
    }

    /**
     * 提交任务
     */
    public Future<?> submit(Runnable task) {
        initTThreadPoolExecutor();
        return mExecutor.submit(task);
    }

    /**
     * 移除任务
     */
    public void remove(Runnable task) {
        initTThreadPoolExecutor();
        mExecutor.getQueue().remove(task);
        LogUtils.i("移除任务--" + task);
    }
}
