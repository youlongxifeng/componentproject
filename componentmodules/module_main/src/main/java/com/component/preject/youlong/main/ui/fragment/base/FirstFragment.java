package com.component.preject.youlong.main.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.ui.fragment.home.HomeFragment;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 15:41
 * @description: （添加一句描述）
 */
public class FirstFragment extends BaseMainFragment {

    public static FirstFragment newInstance() {

        Bundle args = new Bundle();

        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_main, container, false);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        if (findChildFragment(HomeFragment.class) == null) {
            // ShopFragment是flow包里的
            loadRootFragment(R.id.fl_base_container, HomeFragment.newInstance());
        }
    }
}
