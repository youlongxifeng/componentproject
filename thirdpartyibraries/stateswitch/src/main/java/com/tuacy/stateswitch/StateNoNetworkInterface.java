package com.tuacy.stateswitch;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/11/16 9:42
 * @description: （添加一句描述）
 */
public interface StateNoNetworkInterface {

    interface OnRetryListener {

        void onRetry();
    }

    /**
     * 获取实体视图
     *
     * @return 实体视图
     */
    @NonNull
    View getView();

    /**
     * 设置提示信息
     */
    void setPromptMessage(String msg);

    /**
     * 设置重试监听
     */
    void setOnRetryListener(OnRetryListener listener);

    /**
     * View是否显示(有的时候可能会启动一些动画，可以在这里做开始停止的动作)
     */
    void visible(boolean visible);
}
