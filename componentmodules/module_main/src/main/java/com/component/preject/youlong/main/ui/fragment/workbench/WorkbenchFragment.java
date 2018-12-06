package com.component.preject.youlong.main.ui.fragment.workbench;

import android.os.Bundle;
import android.view.View;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.fragment.BaseMvpFragment;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.ui.fragment.todo.ToDoFragment;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 14:55
 * @description: （工作台）
 */
public class WorkbenchFragment extends BaseMvpFragment {
    public static WorkbenchFragment newInstance() {
        Bundle args = new Bundle();
        WorkbenchFragment fragment = new WorkbenchFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_workbench_main;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initDate() {

    }
}
