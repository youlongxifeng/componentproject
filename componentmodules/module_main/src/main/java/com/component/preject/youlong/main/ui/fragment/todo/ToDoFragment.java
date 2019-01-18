package com.component.preject.youlong.main.ui.fragment.todo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.component.preject.youlong.main.R2;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.fragment.BaseMvpFragment;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.bean.WxArticleListItemBean;
import com.component.preject.youlong.main.bean.movie.DataBean;
import com.component.preject.youlong.main.ui.fragment.movie.MovieFragment;
import com.component.preject.youlong.main.ui.fragment.searech.SearechFragment;
import com.component.preject.youlong.main.ui.fragment.todo.adapter.BannerAdapter;
import com.component.preject.youlong.main.ui.fragment.todo.adapter.HomeTabFragmentPagerAdapter;
import com.component.preject.youlong.utils.BlurUtil;
import com.component.preject.youlong.utils.DensityUtils;
import com.component.preject.youlong.utils.LogUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.leochuan.AutoPlayRecyclerView;
import com.leochuan.CarouselLayoutManager;
import com.leochuan.ViewPagerLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 14:54
 * @description: （工具栏）
 */
public class ToDoFragment extends BaseMvpFragment<ToDoPresenter> implements ToDoContract.View,ViewPager.OnPageChangeListener {
    /**
     * 每页显示的数据量
     */
    private static final int PAGE_SIZE = 10;

    @BindView(R2.id.img_bg)
    ImageView imgBg;
    @BindView(R2.id.appbar)
    AppBarLayout appbar;
    @BindView(R2.id.toobar)
    Toolbar toobar;
    @BindView(R2.id.square)
    ImageView square;
    @BindView(R2.id.downtask)
    ImageView downtask;
    @BindView(R2.id.content_main)
    CoordinatorLayout contentMain;

    /**
     * 磁力搜索
     */
    @BindView(R2.id.reclist)
    TextView recList;
    /**
     * 下载中心
     */
    @BindView(R2.id.bangdan)
    TextView bangdan;
    /**
     * 高分整理
     */
    @BindView(R2.id.douban)
    TextView douban;
    /**
     * 分类频道
     */
    @BindView(R2.id.catfrag)
    TextView catfrag;

    @BindView(R2.id.downCenter)
    TextView downCenter;

    @BindView(R2.id.recycler)
    AutoPlayRecyclerView mBannerRecyclerView;
    @BindView(R2.id.home_tabs)
    SlidingTabLayout mSlidingTabLayout;
    @BindView(R2.id.homepager)
    ViewPager mHomeViewPager;

    private CarouselLayoutManager carouselLayoutManager;
    private BannerAdapter mBannerAdapter;
    private HomeTabFragmentPagerAdapter tabFragmentPagerAdapter;
    private String poster;
    List<DataBean> articleList = new ArrayList<>();
    List<Fragment> listfragment=new ArrayList<>();

    public static ToDoFragment newInstance() {
        Bundle args = new Bundle();
        ToDoFragment fragment = new ToDoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected ToDoPresenter createPresenter() {
        return new ToDoPresenter();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_todo_main;
    }

    @Override
    protected void initView(View view) {
        listfragment.add(MovieFragment.newInstance(new Bundle()));
        listfragment.add(MovieFragment.newInstance(new Bundle()));
        tabFragmentPagerAdapter=new HomeTabFragmentPagerAdapter(getActivity().getSupportFragmentManager(),listfragment);
        mHomeViewPager.setAdapter(tabFragmentPagerAdapter);
        mSlidingTabLayout.setViewPager(mHomeViewPager);
    }

    @Override
    protected void initDate() {
        initRecyclerView();
        initAdapter();
        mPresenter.getWxTabs();
    }

    /**
     * 初始化initRecyclerView
     */
    private void initRecyclerView() {
        carouselLayoutManager = new CarouselLayoutManager(getContext(), DensityUtils.dp2px(getContext(), 100));
        carouselLayoutManager.setItemSpace(DensityUtils.dp2px(getContext(), 80));
        carouselLayoutManager.setMoveSpeed(0.3f);
        mBannerRecyclerView.setLayoutManager(carouselLayoutManager);
        mBannerRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_LONG).show();

            }
        });

        carouselLayoutManager.setOnPageChangeListener(new ViewPagerLayoutManager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                LogUtils.i("YYYY","onPageScrollStateChanged== postion="+i);
                if (i == 2) {
                    if (carouselLayoutManager.getCurrentPosition() == 9) {
                        poster = mBannerAdapter.getData().get(0).getDownimgurl().split(",")[0];
                    } else {
                        int postion = carouselLayoutManager.getCurrentPosition() ;
                        LogUtils.i("YYYY","getData=="+mBannerAdapter.getData().size()+" postion="+postion);
                        if (postion < mBannerAdapter.getData().size()) {
                            poster = mBannerAdapter.getData().get(postion).getDownimgurl().split(",")[0];
                        }
                    }
                    if (isDetached()) {
                        return;
                    }
                    if (getContext() == null) {
                        return;
                    }


                    Glide.with(getContext())
                            .asBitmap()
                            .load(poster)
                            .into(new BitmapImageViewTarget(imgBg) {
                                      @Override
                                      public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                          super.onResourceReady(resource, transition);
                                          Bitmap reverseBitmapById = BlurUtil.getBlurBitmap(4, 4, resource);
                                          imgBg.setImageBitmap(reverseBitmapById);
                                      }
                                  }
                            );
                }

            }
        });
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        mBannerAdapter = new BannerAdapter(R.layout.item_banner_viewpager_main, articleList);
        mBannerRecyclerView.setAdapter(mBannerAdapter);
        mBannerAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        }, mBannerRecyclerView);

    }

    @Override
    public void loadBtData(List<DataBean> dataBeanList) {
        setData(true, dataBeanList);
    }

    /**
     * 更新数据集合
     *
     * @param isRefresh
     * @param data
     */
    private void setData(boolean isRefresh, List<DataBean> data) {

        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mBannerAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mBannerAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mBannerAdapter.loadMoreEnd(isRefresh);
            Toast.makeText(getActivity(), "no more data", Toast.LENGTH_SHORT).show();
        } else {
            mBannerAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        if (articleList != null && articleList.size() > 0) {
            int currentItem = i;
            String poster = articleList.get(currentItem % 10).getDownimgurl().split(",")[0];

            Glide.with(getContext())
                    .asBitmap()
                    .load(poster)
                    .into(new BitmapImageViewTarget(imgBg) {
                              @Override
                              public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                  super.onResourceReady(resource, transition);
                                  Bitmap reverseBitmapById = BlurUtil.getBlurBitmap(4, 4, resource);
                                  imgBg.setImageBitmap(reverseBitmapById);
                              }
                          }
                    );

        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}

