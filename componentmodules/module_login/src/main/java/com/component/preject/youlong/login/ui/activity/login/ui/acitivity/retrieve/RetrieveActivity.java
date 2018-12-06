package com.component.preject.youlong.login.ui.activity.login.ui.acitivity.retrieve;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.component.preject.youlong.login.R;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 10:43
 * @description: （取回密码）
 */
public class RetrieveActivity extends AppCompatActivity {
    public static void show(Context context) {
        Intent intent = new Intent(context, RetrieveActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_main);
    }
}
