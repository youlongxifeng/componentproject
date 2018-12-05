package com.component.preject.youlong.main;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/5 14:18
 * @description: （添加一句描述）
 */
public class CustomView extends View {


    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context,   AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
