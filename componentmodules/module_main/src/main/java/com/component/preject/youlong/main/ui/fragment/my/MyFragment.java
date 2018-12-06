package com.component.preject.youlong.main.ui.fragment.my;

import android.os.Bundle;
import android.view.View;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.fragment.BaseMvpFragment;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.ui.fragment.home.HomeFragment;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 15:00
 * @description: （添加一句描述）
 */
public class MyFragment extends BaseMvpFragment {
    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_my_main;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initDate() {

    }
}
