package com.component.preject.youlong.main.ui.fragment.base;

import android.content.Context;
import com.component.preject.youlong.base.mvp.fragment.BaseMvpFragment;
import com.component.preject.youlong.main.ui.fragment.home.HomeFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 15:39
 * @description: （添加一句描述）
 */
public abstract class BaseMainFragment extends SupportFragment {
    protected OnBackToFirstListener _mBackToFirstListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBackToFirstListener) {
            _mBackToFirstListener = (OnBackToFirstListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnBackToFirstListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _mBackToFirstListener = null;
    }

    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
            if (this instanceof FirstFragment) {   // 如果是 第一个Fragment 则退出app
                _mActivity.finish();
            } else {                                    // 如果不是,则回到第一个Fragment
                _mBackToFirstListener.onBackToFirstFragment();
            }
        }
        return true;
    }

    public interface OnBackToFirstListener {
        void onBackToFirstFragment();
    }
}
