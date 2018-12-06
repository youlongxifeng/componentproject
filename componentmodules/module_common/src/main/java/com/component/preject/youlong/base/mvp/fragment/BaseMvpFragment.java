package com.component.preject.youlong.base.mvp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.component.preject.youlong.base.mvp.activity.BaseMvpActivity;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.BaseView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 11:39
 * @description: （mvp Fragment基类）
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends SupportFragment implements BaseView, View.OnTouchListener {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    protected P mPresenter;
    @Nullable
    protected BaseMvpActivity mBaseActivity;
    Unbinder mUnbinder;


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();//创建Presenter
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        if (getActivity() instanceof BaseMvpActivity) {
            mBaseActivity = (BaseMvpActivity) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getlayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initDate();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if(mUnbinder!=null){
            mUnbinder.unbind();
        }
    }

    protected abstract P createPresenter();

    protected abstract int getlayoutId();

    protected abstract void initView(View view);

    protected abstract void initDate();

}
