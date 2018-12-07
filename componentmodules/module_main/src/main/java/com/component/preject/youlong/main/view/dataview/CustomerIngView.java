package com.component.preject.youlong.main.view.dataview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.component.preject.youlong.main.R;
import com.tuacy.stateswitch.StateIngInterface;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/11/16 10:49
 * @description: （添加一句描述）
 */

public class CustomerIngView extends LinearLayout implements StateIngInterface {

    private ImageView mSVGIng;
    private SpreadTextView mTextLoading;

    public CustomerIngView(@NonNull Context context) {
        this(context, null);
    }

    public CustomerIngView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerIngView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_customer_ing, this, true);
        mSVGIng = findViewById(R.id.svg_status_loading);
        mTextLoading = findViewById(R.id.spread_loading_text);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(mSVGIng, "rotation", 0f, 359f);//最好是0f到359f，0f和360f的位置是重复的
        rotation.setRepeatCount(ObjectAnimator.INFINITE);//重复的次数，默认为0，必须是int，可以为-1表示不停止
        rotation.setInterpolator(new LinearInterpolator());//表示变化率，但不是运行速度。一个插补属性，可以将动画效果设置为加速，减速，反复，反弹等。默认为开始和结束慢中间快
        rotation.setDuration(1000);//表示从android:fromDegrees转动到android:toDegrees所花费的时间，单位为毫秒。可以用来计算速度
        rotation.start();
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setPromptMessage(String msg) {
        mTextLoading.setText(msg);
    }

    @Override
    public void visible(boolean visible) {
       /* if (visible) {
            mSVGIng.startAnimation();
        } else {
            mSVGIng.pauseAnimation();
        }*/
    }
}
