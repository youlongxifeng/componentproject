package com.component.preject.youlong.main.ui.fragment.searech;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.component.preject.youlong.base.mvp.fragment.BaseMvpFragment;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.R2;
import com.component.preject.youlong.main.bean.HotKeyBean;
import com.component.preject.youlong.main.common.Constants;
import com.component.preject.youlong.main.ui.fragment.searech.adapter.FlowLayoutAdapter;
import com.component.preject.youlong.main.ui.fragment.searech.adapter.SearechAdapter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/25 14:42
 * @description: （添加一句描述）
 */
public class SearechFragment extends BaseMvpFragment<SearechPresenter> implements SearechContract.View{
    @BindView(R2.id.appbar)
    AppBarLayout appbar;
    @BindView(R2.id.flow_search)
    TagFlowLayout flowSearch;
    @BindView(R2.id.tv_clear)
    TextView tvClear;
    @BindView(R2.id.rv_history)
    RecyclerView rvHistory;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    FlowLayoutAdapter mFlowLayoutAdapter;
    SearechAdapter mSearechAdapter;
    /**
     * 热搜 词语
     */
    private List<HotKeyBean> hotBeans;
    /**
     * 本地保存历史记录
     */
    private List<String> historyList;

    public static SearechFragment newInstance() {

        Bundle args = new Bundle();

        SearechFragment fragment = new SearechFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected SearechPresenter createPresenter() {
        return new SearechPresenter();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_searech_main;
    }

    @Override
    protected void initView(View view) {
        hotBeans = new LinkedList<>();
        historyList = new ArrayList<>();
        initRecyclerView();
        initAdapter();
        initFlowLayout();
    }

    @Override
    protected void initDate() {
        mPresenter.getHotListResult();
        mPresenter.getHistoryList(getActivity(),historyList);
    }

    /**
     * 初始化initRecyclerView
     */
    private void initRecyclerView() {
        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        //mRecyclerView.addItemDecoration(new RecItemDecoration(this, R.color.toast_bg));//等间距分割以及分割线效果
        rvHistory.addOnItemTouchListener(new OnItemClickListener() {
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
        mSearechAdapter = new SearechAdapter(R.layout.item_home_page_main, historyList);
        rvHistory.setAdapter(mSearechAdapter);
        mSearechAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);//加载显示动画
    }

    /**
     * 初始化 flowLayout
     */
    private void initFlowLayout() {
        mFlowLayoutAdapter = new FlowLayoutAdapter(getActivity(), hotBeans);
        flowSearch.setAdapter(mFlowLayoutAdapter);
        flowSearch.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String name = hotBeans.get(position).getName();
                if (!historyList.contains(name)) {
                    historyList.add(name);
                    mPresenter.saveHistory(getActivity(), historyList);
                }
                Bundle bundle = new Bundle();
                bundle.putString(Constants.SEARCH_RESULT_KEY, name);
                //JumpUtil.overlay(context, SearechDetailActivity.class, bundle);
                return true;
            }
        });
    }

    @Override
    public void getHotListOk(List<HotKeyBean> beanList) {
        hotBeans.clear();
        hotBeans.addAll(beanList);
        mSearechAdapter.notifyDataSetChanged();
        initFlowLayout();
    }

    @Override
    public void getHotListErr(String err) {

    }
}
