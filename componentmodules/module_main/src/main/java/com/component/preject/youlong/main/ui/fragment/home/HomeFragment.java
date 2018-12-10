package com.component.preject.youlong.main.ui.fragment.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.fragment.BaseMvpFragment;
import com.component.preject.youlong.common.RouteConfig;
import com.component.preject.youlong.eventbus.SmartEvents;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.utils.LogUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 14:54
 * @description: （添加一句描述）
 */
public class HomeFragment extends BaseMvpFragment {
    private final static String TAG=HomeFragment.class.getSimpleName();
    private TextView mLogIn;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_home_main;
    }

    @Override
    protected void initView(View view) {
        mLogIn = view.findViewById(R.id.text_logon);
        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RouteConfig.LOGIN_MAIN).navigation();
            }
        });
    }

    @Override
    protected void initDate() {
        SmartEvents.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SmartEvents.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Object message) {
        LogUtils.i(TAG, "onMessageEvent message===" + message);
    }
}
