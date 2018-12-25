package com.component.preject.youlong.main.ui.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.component.preject.youlong.base.BaseApplication;
import com.component.preject.youlong.base.mvp.fragment.BaseMvpFragment;
import com.component.preject.youlong.eventbus.SmartEvents;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.R2;
import com.component.preject.youlong.main.bean.BenarBean;
import com.component.preject.youlong.main.bean.DatasBean;
import com.component.preject.youlong.main.bean.HomePageArticleBean;
import com.component.preject.youlong.main.bean.UserInfo;
import com.component.preject.youlong.main.common.Constants;
import com.component.preject.youlong.main.ui.fragment.base.SecondFragment;
import com.component.preject.youlong.main.ui.fragment.home.adapter.HomePageAdapter;
import com.component.preject.youlong.main.ui.fragment.searech.SearechFragment;
import com.component.preject.youlong.main.view.dataview.CustomerEmptyView;
import com.component.preject.youlong.main.view.dataview.CustomerErrorView;
import com.component.preject.youlong.main.view.dataview.CustomerIngView;
import com.component.preject.youlong.main.view.dataview.CustomerNoNetWorkView;
import com.component.preject.youlong.utils.GlideImageLoader;
import com.component.preject.youlong.utils.LogUtils;
import com.tuacy.stateswitch.StateEmptyInterface;
import com.tuacy.stateswitch.StateErrorInterface;
import com.tuacy.stateswitch.StateLayout;
import com.tuacy.stateswitch.StateNoNetworkInterface;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 14:54
 * @description: （添加一句描述）
 */
public class HomeFragment extends BaseMvpFragment<HomePagePresenter> implements HomeContract.View {
    private final static String TAG = HomeFragment.class.getSimpleName();
    /**
     * 总页数
     */
    private int total = 0;
    @BindView(R2.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R2.id.home_state_layout_display)
    StateLayout mMainStateLayout;
    @BindView(R2.id.refresh_wx_list)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R2.id.xrv_wx_list)
    RecyclerView mRecyclerView;

    LinearLayout bannerView;
    private Banner banner;
    private HomePageAdapter mHomePageAdapter;
    private List<DatasBean> articleList = new ArrayList<>();

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected HomePagePresenter createPresenter() {
        return new HomePagePresenter();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_home_main;
    }

    @Override
    protected void initView(View view) {
        //ARouter.getInstance().build(RouteConfig.LOGIN_MAIN).navigation();
        initStateLayout();
        initRefreshLayout();
        initRecyclerView();
        initAdapter();
        refresh();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        //getActivity().getMenuInflater().inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.main_menu_search) {
            start(SearechFragment.newInstance());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initDate() {
        SmartEvents.register(this);

        mPresenter.getBanner();
        mPresenter.getHomepageListData(0);
    }

    private void initbannerView() {
        bannerView = (LinearLayout) getLayoutInflater().inflate(R.layout.home_view_banner, null);
        banner = bannerView.findViewById(R.id.banner);
        bannerView.removeView(banner);
        bannerView.addView(banner);
    }

    private void initStateLayout() {
        mMainStateLayout.setEmptyStateView(new CustomerEmptyView(getActivity()));
        mMainStateLayout.setIngStateView(new CustomerIngView(getActivity()));
        mMainStateLayout.setErrorStateView(new CustomerErrorView(getActivity()));
        mMainStateLayout.setNoNetworkStateView(new CustomerNoNetWorkView(getActivity()));
        mMainStateLayout.setEmptyStateRetryListener(
                new StateEmptyInterface.OnRetryListener() {
                    @Override
                    public void onRetry() {//没数据
                        mMainStateLayout.switchState(StateLayout.State.CONTENT);
                        refresh();

                    }
                });

        mMainStateLayout.setErrorStateRetryListener(
                new StateErrorInterface.OnRetryListener() {
                    @Override
                    public void onRetry() {//数据加载错误
                        mMainStateLayout.switchState(StateLayout.State.CONTENT);
                        refresh();

                    }
                });
        mMainStateLayout.setNoNetworkStateRetryListener(
                new StateNoNetworkInterface.OnRetryListener() {
                    @Override
                    public void onRetry() {//打开网络页面
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)); //直接进入手机中的wifi网络设置界面
                    }
                }

        );
    }

    /**
     * 初始化RefreshLayout
     */
    private void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LogUtils.i(TAG, "initRefreshLayout=============");
                mPresenter.autoRefresh();
            }
        });
    }

    /**
     * 初始化initRecyclerView
     */
    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //mRecyclerView.addItemDecoration(new RecItemDecoration(this, R.color.toast_bg));//等间距分割以及分割线效果
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_LONG).show();
                start(SearechFragment.newInstance());
            }
        });
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        mHomePageAdapter = new HomePageAdapter(R.layout.item_home_page_main, articleList);
        mRecyclerView.setAdapter(mHomePageAdapter);
        initbannerView();
        mHomePageAdapter.addHeaderView(bannerView);
        mHomePageAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);//加载显示动画
        mHomePageAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadMore();
            }
        }, mRecyclerView);

    }

    /**
     * 刷新数据
     */
    private void refresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mHomePageAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        mPresenter.autoRefresh();
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

    @Override
    public void getHomepageListOk(HomePageArticleBean dataBean, boolean isRefresh) {
        mSwipeRefreshLayout.setRefreshing(false);
        setData(isRefresh, dataBean.getDatas());
        LogUtils.i(TAG, "getHomepageListOk  isRefresh=" + isRefresh);
    }

    @Override
    public void getHomepageListErr(String info, boolean isRefresh) {
        if (isRefresh) {
            mMainStateLayout.switchState(StateLayout.State.EMPTY);
        } else {
            mHomePageAdapter.loadMoreFail();//加载更多失败
        }
        LogUtils.i(TAG, "getHomepageListErr  info=" + info);
    }

    @Override
    public void getBannerOk(List<BenarBean> bannerBean) {
        List<String> linkList = new ArrayList<>();
        List<String> imageList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        for (BenarBean benarBean : bannerBean) {
            imageList.add(benarBean.getImagePath());
            titleList.add(benarBean.getTitle());
            linkList.add(benarBean.getUrl());
        }
        if (!getActivity().isDestroyed()) {
            // banner git 地址 https://github.com/youth5201314/banner
            banner.setImageLoader(new GlideImageLoader())
                    .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                    .setImages(imageList)
                    .setBannerAnimation(Transformer.Accordion)
                    .setBannerTitles(titleList)
                    .isAutoPlay(true)
                    .setDelayTime(5000)
                    .setIndicatorGravity(BannerConfig.RIGHT)
                    .start();
        }
    }

    @Override
    public void getBannerErr(String info) {

    }

    @Override
    public void loginOk(UserInfo userInfo) {

    }

    @Override
    public void loginErr(String err) {

    }


    /**
     * 更新数据集合
     *
     * @param isRefresh
     * @param data
     */
    private void setData(boolean isRefresh, List<DatasBean> data) {
        final int size = data == null ? 0 : data.size();
        LogUtils.i(TAG, "size=" + size + "  isRefresh=" + isRefresh);
        if (isRefresh) {
            mHomePageAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mHomePageAdapter.addData(data);
            }
        }
        if (size < Constants.PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mHomePageAdapter.loadMoreEnd(isRefresh);
            Toast.makeText(getActivity(), "no more data", Toast.LENGTH_SHORT).show();
        } else {
            mHomePageAdapter.loadMoreComplete();
        }
    }

}
