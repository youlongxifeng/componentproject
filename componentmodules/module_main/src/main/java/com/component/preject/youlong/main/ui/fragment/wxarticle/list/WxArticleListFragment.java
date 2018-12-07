package com.component.preject.youlong.main.ui.fragment.wxarticle.list;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.fragment.BaseMvpFragment;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.bean.WxArticleBean;
import com.component.preject.youlong.main.bean.WxArticleListBean;
import com.component.preject.youlong.main.bean.WxArticleListItemBean;
import com.component.preject.youlong.main.ui.fragment.wxarticle.list.adapter.WxArticleListRefreshAdapter;
import com.component.preject.youlong.main.view.dataview.CustomerEmptyView;
import com.component.preject.youlong.main.view.dataview.CustomerErrorView;
import com.component.preject.youlong.main.view.dataview.CustomerIngView;
import com.component.preject.youlong.main.view.dataview.CustomerNoNetWorkView;
import com.component.preject.youlong.utils.LogUtils;
import com.tuacy.stateswitch.StateEmptyInterface;
import com.tuacy.stateswitch.StateErrorInterface;
import com.tuacy.stateswitch.StateLayout;
import com.tuacy.stateswitch.StateNoNetworkInterface;
import okhttp3.Request;

import java.util.List;

import static com.tuacy.stateswitch.StateLayout.State.CONTENT;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 17:28
 * @description: （微信公众号详情列表）
 */
public class WxArticleListFragment extends BaseMvpFragment<WxArticleListPresenter> implements WxArticleListContract.View {
    private final static String TAG=WxArticleListFragment.class.getSimpleName();
    public static final String WXARTICLE_BEAN = "wxArticleBean";
    /**
     * 每页显示的数据量
     */
    private static final int PAGE_SIZE = 6;
    private StateLayout mStateLayout;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private WxArticleListRefreshAdapter mAdapter;
    private List<WxArticleListItemBean> datas;
    private WxArticleBean wxArticleListBean;
    private int mNextRequestPage = 1;

    public static WxArticleListFragment newInstance(Bundle bundle) {
        WxArticleListFragment wxArticleListFragment = new WxArticleListFragment();
        wxArticleListFragment.setArguments(bundle);
        return wxArticleListFragment;
    }

    @Override
    protected WxArticleListPresenter createPresenter() {
        return new WxArticleListPresenter();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_wxarticlelist_main;
    }

    @Override
    protected void initView(View view) {
        mStateLayout = view.findViewById(R.id.state_layout_display2);
        mSwipeRefreshLayout = view.findViewById(R.id.refresh_wx_list);
        mRecyclerView = view.findViewById(R.id.xrv_wx_list);
    }
  @Override
    protected void initDate() {
        wxArticleListBean= (WxArticleBean) getArguments().getSerializable(WXARTICLE_BEAN);
        initStateLayout();//初始化StateLayout
        initRefreshLayout();//初始化RefreshLayout
        initRecyclerView();
        initAdapter();//初始化适配器
        refresh();
    }

    @Override
    public void setLoadingSucceeded(WxArticleListBean wxArticleListBean) {
        setData(true, wxArticleListBean.getDatas());
        mAdapter.setEnableLoadMore(true);
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void setLoadingFail(String fail) {
        mAdapter.setEnableLoadMore(true);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setLoadMoreSucceeded(WxArticleListBean wxArticleListBean) {
        boolean isRefresh = mNextRequestPage == 1;
        setData(isRefresh, wxArticleListBean.getDatas());
    }

    @Override
    public void setLoadMoreFail(String fail) {
        mAdapter.loadMoreFail();
    }


    /**
     * 属性数据
     */
    private void refresh() {
        LogUtils.i(TAG,"refresh============mNextRequestPage="+mNextRequestPage);
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        mPresenter.getWxList(wxArticleListBean.getId(),mNextRequestPage);
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        mPresenter.getLoadMoreWxList(wxArticleListBean.getId(),mNextRequestPage);
    }

    /**
     * 更新数据集合
     * @param isRefresh
     * @param data
     */
    private void setData(boolean isRefresh, List<WxArticleListItemBean> data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
            Toast.makeText(getActivity(), "no more data", Toast.LENGTH_SHORT).show();
        } else {
            mAdapter.loadMoreComplete();
        }
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        mAdapter = new WxArticleListRefreshAdapter(0, datas);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);//加载显示动画
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, mRecyclerView);

    }

    /**
     * 初始化RefreshLayout
     */
    private void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LogUtils.i(TAG,"initRefreshLayout=============");
                refresh();
            }
        });
    }
    /**
     * 初始化initRecyclerView
     */
    private void initRecyclerView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //mRecyclerView.addItemDecoration(new RecItemDecoration(this, R.color.toast_bg));//等间距分割以及分割线效果
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 初始化StateLayout
     */
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



}
