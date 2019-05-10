package com.component.preject.youlong.main.ui.fragment.my;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import cn.image.loader.library.loader.ImageLoader;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.fragment.BaseMvpFragment;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.R2;
import com.component.preject.youlong.main.ui.fragment.home.HomeFragment;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 15:00
 * @description: （添加一句描述）
 */
public class MyFragment extends BaseMvpFragment {
    @BindView(R2.id.img_view)
    ImageView imageView;

    String url;
    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_my_main;
    }

    @Override
    protected void initView(View view) {
        url = "http://img2.baa.bitautotech.com/usergroup/editor_pic/2017/3/22/694494c2f3544226ae911bf86b4e2bcc.png";

         ImageLoader.with(getActivity()).load(url).into(imageView);
        Log.i("YYYY","url="+url);
    }

    @Override
    protected void initDate() {

    }
}
