package com.component.preject.youlong.main.ui.fragment.searech.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.component.preject.youlong.main.R;

import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/25 15:09
 * @description: （添加一句描述）
 */
public class SearechAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public SearechAdapter(int layoutResId, @Nullable List<String> data) {
        super(R.layout.item_search_history, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_history,item);
        helper.addOnClickListener(R.id.image_close);
    }
}

