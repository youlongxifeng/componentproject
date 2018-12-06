package com.component.preject.youlong.base.mvp.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.BaseView;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 11:27
 * @description: （添加一句描述）
 */
public abstract class BaseMvpActivity <P extends BasePresenter> extends SupportActivity implements BaseView {
    public P mPresenter;
    Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResID = getlayoutId();
        if (layoutResID != 0) {
            setContentView(layoutResID);
            mUnbinder = ButterKnife.bind(this);
            mPresenter = setPresenter();
            if (mPresenter != null) {
                mPresenter.attachView(this);
            }
        }
        initView();
        initDate();
    }


    @Override
    protected void onStart() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            //不加这个clearFlags好像就没效果
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //设置导航栏透明(就是虚拟键那一栏)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏(或者叫通知栏)透明
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        super.onStart();
    }

    //由于某些工具类，需要在setContentView的顺序前或者后来编辑
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

    }

    public abstract P setPresenter();

    public abstract int getlayoutId();

    public abstract void initView();

    public abstract void initDate();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.unSubscribe();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }


}

