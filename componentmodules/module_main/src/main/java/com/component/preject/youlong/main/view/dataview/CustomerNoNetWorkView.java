package com.component.preject.youlong.main.view.dataview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.component.preject.youlong.main.R;
import com.tuacy.stateswitch.StateNoNetworkInterface;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/11/16 10:53
 * @description: （添加一句描述）
 */

public class CustomerNoNetWorkView extends LinearLayout implements StateNoNetworkInterface {

    private ImageView mSVGError;
    private TextView mTextRefresh;
    private StateNoNetworkInterface.OnRetryListener mRetryListener;

    public CustomerNoNetWorkView(@NonNull Context context) {
        this(context, null);
    }

    public CustomerNoNetWorkView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerNoNetWorkView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_customer_no_network, this, true);
        mSVGError = findViewById(R.id.svg_status_exception);
        mTextRefresh = findViewById(R.id.text_status_layout_exception_refresh);
        mTextRefresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRetryListener != null) {
                    mRetryListener.onRetry();
                }
            }
        });
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setPromptMessage(String msg) {
        mTextRefresh.setText(msg);
    }

    @Override
    public void setOnRetryListener(OnRetryListener listener) {
        mRetryListener = listener;
    }



    @Override
    public void visible(boolean visible) {
        /*if (visible) {
            mSVGError.startAnimation();
        } else {
            mSVGError.pauseAnimation();
        }*/
    }
}
