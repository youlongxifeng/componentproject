package com.component.preject.youlong.main.ui.fragment.searech.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.bean.HotKeyBean;
import com.component.preject.youlong.main.utils.ColorUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/25 15:15
 * @description: （添加一句描述）
 */
public class FlowLayoutAdapter extends TagAdapter<HotKeyBean> {

    Context mContext;

    public FlowLayoutAdapter(Context context,List<HotKeyBean> datas) {
        super(datas);
        mContext=context;
    }

    @Override
    public View getView(FlowLayout parent, int position, HotKeyBean hotKeyBean) {
        TextView text = (TextView) parent.inflate(mContext, R.layout.item_searech_textview_hot, null);
        String name = hotKeyBean.getName();
        text.setText(name);
        text.setTextColor(ColorUtil.getRandomColor());
        return text;
    }
}