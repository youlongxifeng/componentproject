package com.component.preject.youlong.login.ui.activity.login.ui.acitivity.login;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.component.preject.youlong.base.mvp.activity.BaseMvpActivity;
import com.component.preject.youlong.common.RouteConfig;
import com.component.preject.youlong.login.R;
import com.component.preject.youlong.login.ui.activity.login.ui.acitivity.register.RegisterActivity;
import com.component.preject.youlong.login.ui.activity.login.ui.acitivity.retrieve.RetrieveActivity;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/5 18:11
 * @description: （登录页面）
 */
@Route(path = RouteConfig.LOGIN_MAIN)
public class LogInActivity extends BaseMvpActivity<LogInPresenter> implements View.OnClickListener {
    /**
     * 忘记密码
     */
    private TextView tv_login_forget_pwd;
    /**
     * 登录
     */
    private Button bt_login_submit;
    /**
     * 注册
     */
    private Button bt_login_register;


    @Override
    public LogInPresenter setPresenter() {
        return new LogInPresenter();
    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_sigin_main;
    }

    @Override
    public void initView() {
        tv_login_forget_pwd = findViewById(R.id.tv_login_forget_pwd);
        tv_login_forget_pwd.setOnClickListener(this);
        bt_login_submit = findViewById(R.id.bt_login_submit);
        bt_login_submit.setOnClickListener(this);
        bt_login_register = findViewById(R.id.bt_login_register);
        bt_login_register.setOnClickListener(this);
    }

    @Override
    public void initDate() {

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_login_register) {//注册
            RegisterActivity.show(this);
        } else if (i == R.id.bt_login_submit) {//登录

        } else if (i == R.id.tv_login_forget_pwd) {//忘记密码
            RetrieveActivity.show(this);
        }

    }
}
