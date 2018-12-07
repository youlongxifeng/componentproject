package com.component.preject.youlong.main.ui.fragment.wxarticle.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.component.preject.youlong.main.bean.WxArticleBean;
import com.component.preject.youlong.main.ui.fragment.wxarticle.list.WxArticleListFragment;
import com.component.preject.youlong.utils.LogUtils;

import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 17:24
 * @description: （添加一句描述）
 */
public class PagerFragmentAdapter extends FragmentStatePagerAdapter {
    List<WxArticleBean> wxArticleBeanList;

    public PagerFragmentAdapter(FragmentManager fm, List<WxArticleBean> beanList) {
        super(fm);
        wxArticleBeanList = beanList;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        WxArticleBean wxArticleBean = wxArticleBeanList.get(position);
        bundle.putSerializable(WxArticleListFragment.WXARTICLE_BEAN,wxArticleBean);
        return WxArticleListFragment.newInstance(bundle);
    }

    @Override
    public int getCount() {
        return wxArticleBeanList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence charSequence=wxArticleBeanList.get(position).getName();
        LogUtils.i("YYYY","charSequence===="+charSequence);
        return charSequence;
    }
}
