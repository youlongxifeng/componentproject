package com.component.preject.youlong.main.ui.fragment.wxarticle.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.fragment.BaseMvpFragment;
import com.component.preject.youlong.main.R;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 17:28
 * @description: （微信公众号详情列表）
 */
public class WxArticleListFragment extends BaseMvpFragment {
    public static final String WXARTICLE_BEAN="wxArticleBean";

    public static WxArticleListFragment newInstance(Bundle bundle) {
        WxArticleListFragment wxArticleListFragment=new  WxArticleListFragment();
        wxArticleListFragment.setArguments(bundle);
        return wxArticleListFragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_wxarticlelist_main;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initDate() {

    }
}
