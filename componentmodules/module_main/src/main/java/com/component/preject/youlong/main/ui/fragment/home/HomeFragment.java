package com.component.preject.youlong.main.ui.fragment.home;

import android.os.Bundle;
import android.view.View;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.fragment.BaseMvpFragment;
import com.component.preject.youlong.main.R;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 14:54
 * @description: （添加一句描述）
 */
public class HomeFragment extends BaseMvpFragment {

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

    }

    @Override
    protected void initDate() {

    }
}
