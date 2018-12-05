package com.component.preject.youlong.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/4 14:55
 * @description: （添加一句描述）
 */
public class MainActivity extends AppCompatActivity {


    @BindView(R2.id.my_text)
    TextView myText;
    @BindView(R2.id.my_ll)
    LinearLayout myLl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_main);
        //myLl.addView(new CustomView(this));

    }
}
