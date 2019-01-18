package com.component.preject.youlong.main.ui.fragment.todo.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.component.preject.youlong.main.ui.fragment.movie.MovieFragment;

import java.util.List;

/**
 * @ProjectName: componentproject
 * @Package: com.component.preject.youlong.main.ui.fragment.todo.adapter
 * @ClassName: HomeTabFragmentPagerAdapter
 * @Author: xiezhenggen
 * @CreateDate: 2019/1/18 16:00
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/18 16:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class HomeTabFragmentPagerAdapter extends FragmentStatePagerAdapter {
    String[] tabName = {"最新电影","最新电视剧"};
    private List<Fragment> listfragment;
    public HomeTabFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.listfragment=list;
    }

    @Override
    public Fragment getItem(int i) {
        return MovieFragment.newInstance(new Bundle());
    }

    @Override
    public int getCount() {
        return listfragment.size();
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabName[position];
    }
}
