package com.component.preject.youlong.login.ui.activity.login.ui.acitivity.resetpwd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.component.preject.youlong.login.R;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 10:50
 * @description: （重置密码）
 */
public class ResetPwdActivity extends AppCompatActivity {
    public static void show(Context context) {
        Intent intent = new Intent(context, ResetPwdActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_resetpwd_main);
    }
}
