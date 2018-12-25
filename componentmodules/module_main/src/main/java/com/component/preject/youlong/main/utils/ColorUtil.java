package com.component.preject.youlong.main.utils;

import android.graphics.Color;

import java.util.Random;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/25 15:37
 * @description: （添加一句描述）
 */
public class ColorUtil {
    public static int getRandomColor(){

        Random random = new Random();

        int red = random.nextInt(160);
        int green = random.nextInt(160);
        int blue = random.nextInt(160);

        return Color.rgb(red,green,blue);

    }
}
