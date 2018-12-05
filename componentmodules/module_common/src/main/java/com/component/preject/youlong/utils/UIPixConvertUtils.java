package com.component.preject.youlong.utils;

import android.content.Context;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/4 15:51
 * @description: （像素转换工具类）
 */
public class UIPixConvertUtils {

    /**
     * dp转换px
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
