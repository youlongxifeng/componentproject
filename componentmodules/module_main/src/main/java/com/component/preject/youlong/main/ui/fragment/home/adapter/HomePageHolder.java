package com.component.preject.youlong.main.ui.fragment.home.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.R2;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/25 9:27
 * @description: （添加一句描述）
 */
public class HomePageHolder extends BaseViewHolder {

    @BindView(R2.id.tv_author)
    TextView mTvAuthor;
    @BindView(R2.id.tv_type)
    TextView mTvType;
    @BindView(R2.id.tv_content)
    TextView mTvContent;
    @BindView(R2.id.image_collect)
    ImageView mImageCollect;
    @BindView(R2.id.tv_time)
    TextView mTvTime;
    @BindView(R2.id.tv_tag)
    TextView mTvTag;

    public HomePageHolder(View view) {
        super(view);
    }
}
