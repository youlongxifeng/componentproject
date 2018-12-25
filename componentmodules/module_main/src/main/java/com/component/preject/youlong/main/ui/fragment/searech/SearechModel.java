package com.component.preject.youlong.main.ui.fragment.searech;

import android.content.Context;
import com.component.preject.youlong.main.bean.BaseResponse;
import com.component.preject.youlong.main.bean.HotKeyBean;
import com.component.preject.youlong.main.bean.WxArticleBean;
import com.component.preject.youlong.main.common.Constants;
import com.component.preject.youlong.main.http.HttpClientUtils;
import com.component.preject.youlong.main.utils.PreferencesUtils;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/25 15:24
 * @description: （添加一句描述）
 */
public class SearechModel implements SearechContract.Model {
    @Override
    public Observable<BaseResponse<List<HotKeyBean>>> getHotListResult() {
        return HttpClientUtils.getCommonHttp().getHotListResult();
    }

    @Override
    public void saveHistory(Context context, List<String> historyList) {
        //保存之前先清空之前的存储
        PreferencesUtils.remove(context, Constants.SEARCH_HISTORY);
        //存储
        StringBuilder sb = new StringBuilder();
        if (historyList.size() > 0) {
            for (String s : historyList) {
                sb.append(s).append(",");
            }
            sb.delete(sb.length() - 1, sb.length());
            PreferencesUtils.putString(context, Constants.SEARCH_HISTORY, sb.toString().trim());
        }
    }

    @Override
    public void getHistory(Context context, List<String> historyList) {
        historyList.clear();
        String histories =  PreferencesUtils.getString(context, Constants.SEARCH_HISTORY, Constants.DEFAULT);
        if (!histories.equals(Constants.DEFAULT)) {
            String[] history = histories.split(",");
            historyList.addAll(Arrays.asList(history));
        }
    }
}
