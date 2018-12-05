package com.component.preject.youlong.login.ui.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.component.preject.youlong.common.RouteConfig;
import com.component.preject.youlong.login.R;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/5 18:11
 * @description: （添加一句描述）
 */
@Route(path = RouteConfig.LOGIN_MAIN)
public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin_main);
    }
}
