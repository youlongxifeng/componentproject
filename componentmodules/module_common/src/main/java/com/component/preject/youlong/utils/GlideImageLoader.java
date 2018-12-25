package com.component.preject.youlong.utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.component.preject.youlong.base.BaseApplication;
import com.youth.banner.loader.ImageLoader;
/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/25 11:25
 * @description: （主要用于 结合 banner 加载图片使用）
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(BaseApplication.getContext()).load(path).into(imageView);
    }
}