package com.component.preject.youlong.main.ui.fragment.wxarticle;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.component.preject.youlong.base.mvp.fragment.BaseMvpFragment;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.bean.WxArticleBean;
import com.component.preject.youlong.main.ui.fragment.wxarticle.adapter.PagerFragmentAdapter;
import com.component.preject.youlong.main.view.dataview.CustomerEmptyView;
import com.component.preject.youlong.main.view.dataview.CustomerErrorView;
import com.component.preject.youlong.main.view.dataview.CustomerIngView;
import com.component.preject.youlong.main.view.dataview.CustomerNoNetWorkView;
import com.component.preject.youlong.utils.LogUtils;
import com.tuacy.stateswitch.StateEmptyInterface;
import com.tuacy.stateswitch.StateErrorInterface;
import com.tuacy.stateswitch.StateLayout;
import com.tuacy.stateswitch.StateNoNetworkInterface;

import java.util.List;

import static com.tuacy.stateswitch.StateLayout.State.CONTENT;
import static com.tuacy.stateswitch.StateLayout.State.ERROR;
import static com.tuacy.stateswitch.StateLayout.State.ING;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 14:55
 * @description: （微信公众号）
 */
public class WxArticleFragment extends BaseMvpFragment<WxArticlePresenter> implements WxArticleContract.View {
    private static final String TAG = WxArticleFragment.class.getSimpleName();
    private TabLayout mTab;
    private ViewPager mViewPager;
    private PagerFragmentAdapter pagerFragmentAdapter;
    private StateLayout mStateLayout;

    public static WxArticleFragment newInstance() {
        Bundle args = new Bundle();
        WxArticleFragment fragment = new WxArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected WxArticlePresenter createPresenter() {
        return new WxArticlePresenter();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_workbench_main;
    }

    @Override
    protected void initView(View view) {
        mStateLayout=view.findViewById(R.id.state_layout_display);
        mStateLayout.switchState(ING);
        mTab = view.findViewById(R.id.tab);
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPager = view.findViewById(R.id.vp_wx);

    }
    private void initStateLayout() {
        mStateLayout.setEmptyStateView(new CustomerEmptyView(getActivity()));
        mStateLayout.setIngStateView(new CustomerIngView(getActivity()));
        mStateLayout.setErrorStateView(new CustomerErrorView(getActivity()));
        mStateLayout.setNoNetworkStateView(new CustomerNoNetWorkView(getActivity()));
        mStateLayout.setEmptyStateRetryListener(
                new StateEmptyInterface.OnRetryListener() {
                    @Override
                    public void onRetry() {//没数据
                        mStateLayout.switchState(CONTENT);
                        // mSmartRefreshLayout.autoRefresh();

                    }
                });

        mStateLayout.setErrorStateRetryListener(
                new StateErrorInterface.OnRetryListener() {
                    @Override
                    public void onRetry() {//数据加载错误
                        mStateLayout.switchState(CONTENT);
                        // mSmartRefreshLayout.autoRefresh();

                    }
                });
        mStateLayout.setNoNetworkStateRetryListener(
                new StateNoNetworkInterface.OnRetryListener() {
                    @Override
                    public void onRetry() {//打开网络页面
                      //  startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)); //直接进入手机中的wifi网络设置界面
                    }
                }

        );
    }

    @Override
    protected void initDate() {
        mPresenter.getWxTabs();
    }

    @Override
    public void setLoadingSucceeded(List<WxArticleBean> wxArticleBeanList) {
        LogUtils.i(TAG,"wxArticleBeanList=="+wxArticleBeanList);
        mStateLayout.switchState(CONTENT);
        pagerFragmentAdapter = new PagerFragmentAdapter(getChildFragmentManager(), wxArticleBeanList);
        mViewPager.setAdapter(pagerFragmentAdapter);
        mTab.setupWithViewPager(mViewPager);
    }

    @Override
    public void setLoadingFail(String fail) {
        mStateLayout.switchState(ERROR);
    }
}
