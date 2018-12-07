package com.component.preject.youlong.main.ui.activity.home;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.activity.BaseMvpActivity;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.event.TabSelectedEvent;
import com.component.preject.youlong.main.ui.fragment.base.*;
import com.component.preject.youlong.main.ui.fragment.home.HomeFragment;
import com.component.preject.youlong.main.ui.fragment.my.MyFragment;
import com.component.preject.youlong.main.ui.fragment.todo.ToDoFragment;
import com.component.preject.youlong.main.ui.fragment.wxarticle.WxArticleFragment;
import com.component.preject.youlong.main.widget.bottombar.BottomBar;
import com.component.preject.youlong.main.widget.bottombar.BottomBarTab;
import com.component.preject.youlong.manage.permissions.PermissionsManager;
import com.component.preject.youlong.manage.permissions.PermissionsResultAction;
import com.component.preject.youlong.utils.LogUtils;
import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/4 14:55
 * @description: （应用主页）
 */
public class MainActivity extends BaseMvpActivity implements BaseMainFragment.OnBackToFirstListener, View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS};
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    private SupportFragment[] mFragments = new SupportFragment[4];

    private BottomBar mBottomBar;


    @Override
    public BasePresenter setPresenter() {
        return null;
    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initFragmeng();
        initBottomBar();
    }
    /**
     * 初始化fragment
     */
    private void initFragmeng() {
        SupportFragment firstFragment = findFragment(FirstFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = FirstFragment.newInstance();
            mFragments[SECOND] = SecondFragment.newInstance();
            mFragments[THIRD] = ThirdFragment.newInstance();
            mFragments[FOURTH] = FourthFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(SecondFragment.class);
            mFragments[THIRD] = findFragment(ThirdFragment.class);
            mFragments[FOURTH] = findFragment(FourthFragment.class);
        }
    }

    /***
     * 初始化底部状态栏
     */
    private void initBottomBar() {
        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mBottomBar.addItem(new BottomBarTab(this, R.drawable.ic_home_white_24dp))
                .addItem(new BottomBarTab(this, R.drawable.ic_discover_white_24dp))
                .addItem(new BottomBarTab(this, R.drawable.ic_message_white_24dp))
                .addItem(new BottomBarTab(this, R.drawable.ic_account_circle_white_24dp));
        mBottomBar.setOnTabSelectedListener(tabSelectedListener);
    }

    @Override
    public void initDate() {

    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }
    }

    @Override
    public void onBackToFirstFragment() {
        mBottomBar.setCurrentItem(0);
    }

    /**
     * 请求相机和麦克风权限 测试例子
     */
    private void requestPermission() {
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this, permissions
                , new PermissionsResultAction() {
                    @Override
                    public void onGranted() {
                        LogUtils.i(TAG, "======onGranted===========");
                    }

                    @Override
                    public void onDenied(String permission) {
                        //  UiUtils.getCenterToast("没有获取到权限,请到设置中给予权限" + permission);
                        LogUtils.i(TAG, "======onDenied===========");
                    }
                }
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
        LogUtils.i(TAG, "======onRequestPermissionsResult==========requestCode=" + requestCode);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();

        //跳转到LogInActivity
        // ARouter.getInstance().build(RouteConfig.LOGIN_MAIN).navigation();

    }

    private BottomBar.OnTabSelectedListener tabSelectedListener = new BottomBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position, int prePosition) {
            showHideFragment(mFragments[position], mFragments[prePosition]);
        }

        @Override
        public void onTabUnselected(int position) {
            final SupportFragment currentFragment = mFragments[position];
            int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();
            // 如果不在该类别Fragment的主页,则回到主页; 这段代码需要重新处理一下
            if (count > 1) {
                if (currentFragment instanceof FirstFragment) {
                    currentFragment.popToChild(HomeFragment.class, false);
                } else if (currentFragment instanceof SecondFragment) {
                    currentFragment.popToChild(ToDoFragment.class, false);
                } else if (currentFragment instanceof ThirdFragment) {
                    currentFragment.popToChild(WxArticleFragment.class, false);
                } else if (currentFragment instanceof FourthFragment) {
                    currentFragment.popToChild(MyFragment.class, false);
                }
                return;
            }


            // 这里推荐使用EventBus来实现 -> 解耦
            if (count == 1) {
                // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                EventBusActivityScope.getDefault(MainActivity.this).post(new TabSelectedEvent(position));
            }
        }

        @Override
        public void onTabReselected(int position) {


        }
    };
}
