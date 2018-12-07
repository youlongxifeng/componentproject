package com.component.preject.youlong.main.ui.fragment.wxarticle.list.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.bean.TagBean;
import com.component.preject.youlong.main.bean.WxArticleListItemBean;

import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/7 14:04
 * @description: （添加一句描述）
 */
public class WxArticleListRefreshAdapter extends BaseQuickAdapter<WxArticleListItemBean, WxArticleListRefreshAdapter.WxArticleListViewHolder> {
    public WxArticleListRefreshAdapter(int layoutResId, @Nullable List<WxArticleListItemBean> data) {
        super(R.layout.adapter_wxarticle_item, data);
    }

    @Override
    protected void convert(WxArticleListViewHolder helper, WxArticleListItemBean item) {
        TextView tvAuthor = helper.getView(R.id.tv_adapter_wx_author);
        TextView tvChapter = helper.getView(R.id.tv_adapter_wx_chapter);
        TextView tvTime = helper.getView(R.id.tv_adapter_wx_time);
        TextView tvTitle = helper.getView(R.id.tv_adapter_wx_title);
        LinearLayout llTags = helper.getView(R.id.ll_adapter_wx_tags);
        LinearLayout ll_wx = helper.getView(R.id.ll_wx);
        tvAuthor.setText(item.getAuthor());
        tvChapter.setText(item.getChapterName());
        tvTime.setText(item.getNiceDate());
        tvTitle.setText(item.getTitle());
        List<TagBean> tagBeanList = item.getTags();

    }

    public static class WxArticleListViewHolder extends BaseViewHolder {
      /*  TextView tvAuthor;
        TextView tvChapter;
        TextView tvTime;
        TextView tvTitle;
        LinearLayout llTags;
        LinearLayout ll_wx;*/

        public WxArticleListViewHolder(View view) {
            super(view);
         /*   tvAuthor = view.findViewById(R.id.tv_adapter_wx_author);
            tvChapter = view.findViewById(R.id.tv_adapter_wx_chapter);
            tvTime = view.findViewById(R.id.tv_adapter_wx_time);
            tvTitle = view.findViewById(R.id.tv_adapter_wx_title);
            llTags = view.findViewById(R.id.ll_adapter_wx_tags);
            ll_wx = view.findViewById(R.id.ll_wx);*/
        }


    }
}
