package com.component.preject.youlong.main;

import android.content.Context;
import android.graphics.*;
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

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);
        RectF rel = new RectF(100, 100, 300, 300);
        //实心圆弧
        canvas.drawArc(rel, 0, 270, false, paint);
        //实心圆弧 将圆心包含在内
        RectF rel2 = new RectF(100, 400, 300, 600);
        canvas.drawArc(rel2, 0, 270, true, paint);
        //设置空心Style
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        RectF rel3 = new RectF(100, 700, 300, 900);
        canvas.drawArc(rel3, 0, 270, false, paint);
        RectF rel4 = new RectF(100, 1000, 300, 1200);
        canvas.drawArc(rel4, 0, 270, true, paint);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setColor(Color.BLUE);
        paint2.setTextSize(80);
        canvas.drawText("jEh", 80, 1400, paint2);
    }
}
