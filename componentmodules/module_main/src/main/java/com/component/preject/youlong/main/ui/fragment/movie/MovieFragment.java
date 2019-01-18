package com.component.preject.youlong.main.ui.fragment.movie;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.fragment.BaseMvpFragment;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.R2;
import com.component.preject.youlong.main.bean.movie.DataBean;
import com.component.preject.youlong.main.bean.movie.MovieResponse;
import com.component.preject.youlong.main.common.Constant;
import com.component.preject.youlong.main.common.Constants;
import com.component.preject.youlong.main.ui.fragment.movie.adapter.MovieAdapter;
import com.component.preject.youlong.main.ui.fragment.wxarticle.list.WxArticleListFragment;
import com.component.preject.youlong.main.view.dataview.CustomerEmptyView;
import com.component.preject.youlong.main.view.dataview.CustomerErrorView;
import com.component.preject.youlong.main.view.dataview.CustomerIngView;
import com.component.preject.youlong.main.view.dataview.CustomerNoNetWorkView;
import com.component.preject.youlong.utils.LogUtils;
import com.tuacy.stateswitch.StateEmptyInterface;
import com.tuacy.stateswitch.StateErrorInterface;
import com.tuacy.stateswitch.StateLayout;
import com.tuacy.stateswitch.StateNoNetworkInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.tuacy.stateswitch.StateLayout.State.CONTENT;

/**
 * @ProjectName: componentproject
 * @Package: com.component.preject.youlong.main.ui.fragment.movie
 * @ClassName: MovieFragment
 * @Author: xiezhenggen
 * @CreateDate: 2019/1/18 16:02
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/18 16:02
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class MovieFragment extends BaseMvpFragment<MoviePresenter> implements MovieContract.View {
    private final static String TAG = MovieFragment.class.getSimpleName();
    @BindView(R2.id.movie_state_layout_display)
    StateLayout mStateLayout;
    @BindView(R2.id.refresh_wx_list)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R2.id.xrv_wx_list)
    RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;
    /**
     * 下一页请求页数
     */
    private int mNextRequestPage = 1;
    /**
     * 搜索关键词
     */
    private String keyword = "";
    /**
     * 总页数
     */
    private int total = 0;
    private List<DataBean> datas = new ArrayList<>();

    public static MovieFragment newInstance(Bundle bundle) {
        MovieFragment movieFragment = new MovieFragment();
        movieFragment.setArguments(bundle);
        return movieFragment;
    }

    @Override
    protected MoviePresenter createPresenter() {
        return new MoviePresenter();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_home_seris_main;
    }

    @Override
    protected void initView(View view) {
        initStateLayout();
        initRefreshLayout();//初始化RefreshLayout
        initRecyclerView();
        initAdapter();//初始化适配器
    }

    @Override
    protected void initDate() {
        refresh();
    }

    /**
     * 初始化数据加载页
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
                        refresh();//刷新数据
                    }
                });

        mStateLayout.setErrorStateRetryListener(
                new StateErrorInterface.OnRetryListener() {
                    @Override
                    public void onRetry() {//数据加载错误
                        mStateLayout.switchState(CONTENT);
                        refresh();
                    }
                });
        mStateLayout.setNoNetworkStateRetryListener(
                new StateNoNetworkInterface.OnRetryListener() {
                    @Override
                    public void onRetry() {//打开网络页面
                        //直接进入手机中的wifi网络设置界面
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
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
                refresh();
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
            }
        });


    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        mAdapter = new MovieAdapter(getContext(),R.layout.item_moview_main, datas);
        mRecyclerView.setAdapter(mAdapter);
        //加载显示动画
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, mRecyclerView);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

    }

    /**
     * 刷新数据
     */
    private void refresh() {
        LogUtils.i(TAG, "refresh============mNextRequestPage=" + mNextRequestPage);
        mNextRequestPage = 1;
        mSwipeRefreshLayout.setRefreshing(true);
        //这里的作用是防止下拉刷新的时候还可以上拉加载
        mAdapter.setEnableLoadMore(false);
        mPresenter.getSerisUpdate(mNextRequestPage);
    }

    /**
     * 加载更多数据
     */
    private void loadMore() {
        mPresenter.getSerisUpdate(/*recyclerId,*/ mNextRequestPage);
    }

    /**
     * 更新数据集合
     *
     * @param isRefresh
     * @param data
     */
    private void setData(boolean isRefresh, List<DataBean> data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            datas.clear();
            datas.addAll(data);
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                datas.addAll(data);
                mAdapter.addData(data);
            }
        }
        if (size < Constants.PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
            Toast.makeText(getActivity(), "no more data", Toast.LENGTH_SHORT).show();
        } else {
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void setLoadingSucceeded(int pagecount,MovieResponse<List<DataBean>> movieResponse) {
        boolean isRefresh = mNextRequestPage == 1;
        mSwipeRefreshLayout.setRefreshing(false);
        List<DataBean> dataBeanList=movieResponse.getData();
        //添加新的数据
        setData(isRefresh, dataBeanList);
    }

    @Override
    public void recoveryBoxListIsEmpty(int pagecount) {
        if (pagecount == 1) {
            mStateLayout.switchState(StateLayout.State.EMPTY);
        } else {
            mAdapter.loadMoreFail();
        }
    }

    /**
     * 数据加载失败
     *
     * @param fail
     */
    @Override
    public void setLoadingFail(int pagecount,String fail) {
        mSwipeRefreshLayout.setRefreshing(false);
        if (pagecount == 1) {
            mStateLayout.switchState(StateLayout.State.ERROR, fail);
        } else {
            mAdapter.loadMoreFail();
        }
    }
}
