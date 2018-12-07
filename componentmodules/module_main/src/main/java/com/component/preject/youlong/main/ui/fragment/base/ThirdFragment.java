package com.component.preject.youlong.main.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.ui.fragment.wxarticle.WxArticleFragment;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 15:42
 * @description: （添加一句描述）
 */
public class ThirdFragment extends BaseMainFragment {

    public static ThirdFragment newInstance() {

        Bundle args = new Bundle();

        ThirdFragment fragment = new ThirdFragment();
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
        if (findChildFragment(WxArticleFragment.class) == null) {
            // ShopFragment是flow包里的
            loadRootFragment(R.id.fl_base_container, WxArticleFragment.newInstance());
        }
    }
}
