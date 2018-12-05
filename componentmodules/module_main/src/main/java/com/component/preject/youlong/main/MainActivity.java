package com.component.preject.youlong.main;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alibaba.android.arouter.launcher.ARouter;
import com.component.preject.youlong.common.RouteConfig;
import com.component.preject.youlong.main.R2;
import com.component.preject.youlong.manage.permissions.PermissionsManager;
import com.component.preject.youlong.manage.permissions.PermissionsResultAction;
import com.component.preject.youlong.utils.LogUtils;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/4 14:55
 * @description: （添加一句描述）
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS};

    TextView mLoginTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLoginTextView = findViewById(R.id.tx_login);
        mLoginTextView.setOnClickListener(this);
        requestPermission();
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
        if (i == R.id.tx_login) {
            //跳转到LogInActivity
            ARouter.getInstance().build(RouteConfig.LOGIN_MAIN).navigation();
        } else {
        }
    }
}
