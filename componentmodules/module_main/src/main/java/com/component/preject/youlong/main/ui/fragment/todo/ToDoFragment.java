package com.component.preject.youlong.main.ui.fragment.todo;

import android.os.Bundle;
import android.view.View;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.fragment.BaseMvpFragment;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.ui.fragment.my.MyFragment;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 14:54
 * @description: （工具栏）
 */
public class ToDoFragment extends BaseMvpFragment {
    public static ToDoFragment newInstance() {
        Bundle args = new Bundle();
        ToDoFragment fragment = new ToDoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_todo_main;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initDate() {

    }
}

