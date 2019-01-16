package com.component.preject.youlong.main.view;

import android.content.Context;
import android.graphics.*;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;

import static android.graphics.Paint.FILTER_BITMAP_FLAG;

/**
 * @ProjectName: componentproject
 * @Package: com.component.preject.youlong.main.view
 * @ClassName: PosterItemView
 * @Author: xiezhenggen
 * @CreateDate: 2019/1/16 16:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/16 16:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */

public class PosterItemView extends AppCompatImageView {

    private Bitmap reverseBitmap;
    private Paint bitmapPaint;
    private TextPaint textPaint;
    private String mTitle;
    private Bitmap bitmap;
    private Rect rect;

    public PosterItemView(Context context) {
        this(context,null);
    }

    public PosterItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PosterItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmapPaint.setFlags(FILTER_BITMAP_FLAG);
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(30);

        rect = new Rect(0,0,360,500);

    }

    public void setReverseImageBitmap(Bitmap bitmap){
        this.reverseBitmap = bitmap;
        invalidate();
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (bitmap!=null){
            canvas.drawBitmap(bitmap,null,rect,bitmapPaint);
        }
        if (!TextUtils.isEmpty(mTitle)){
            canvas.drawText(mTitle,getMeasuredWidth()/2,getMeasuredHeight()+30,textPaint);
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top+50, right, bottom);
    }

    public void setTitleText(String downdtitle) {
        this.mTitle = downdtitle;
        invalidate();
    }

    public void setImageBackGround(Bitmap resource) {
        this.bitmap = resource;
        invalidate();
    }
}
