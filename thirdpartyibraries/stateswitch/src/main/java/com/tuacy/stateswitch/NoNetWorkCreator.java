package com.tuacy.stateswitch;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/11/16 9:59
 * @description: （添加一句描述）
 */
public interface NoNetWorkCreator {
    @NonNull
    StateNoNetworkInterface createStateNoNetwork(@NonNull Context context, @NonNull StateLayout layout);
}
