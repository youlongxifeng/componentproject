package com.component.preject.youlong.main.ui.fragment.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.bean.movie.DataBean;
import com.component.preject.youlong.main.ui.fragment.todo.adapter.BannerHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: componentproject
 * @Package: com.component.preject.youlong.main.ui.fragment.movie.adapter
 * @ClassName: MovieAdapter
 * @Author: xiezhenggen
 * @CreateDate: 2019/1/18 16:45
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/18 16:45
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class MovieAdapter extends BaseQuickAdapter<DataBean, BaseViewHolder> {
    private final String time;
    private Context mContext;

    public MovieAdapter(Context context, int layoutResId, @Nullable List<DataBean> data) {
        super(layoutResId, data);
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd");
        Date ss = new Date();
        this.time = format0.format(ss.getTime());
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBean item) {
        ImageView itemimg = helper.getView(R.id.post_img);
        TextView itemtitle = helper.getView(R.id.post_title);
        TextView itemTag = helper.getView(R.id.item_update_tag);
        String imgUrl = item.getDownimgurl();
        String posterImgUrl = imgUrl.split(",")[0];
        Uri uri = Uri.parse(posterImgUrl);

        RequestOptions options = new RequestOptions()
                //加载成功之前占位图
                .placeholder(R.drawable.ic_empty)
                //加载错误之后的错误图
                .error(R.drawable.ic_empty)
                //跳过内存缓存
                .skipMemoryCache(true)
                //缓存所有版本的图像
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //跳过磁盘缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                //只缓存原来分辨率的图片
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                //只缓存最终的图片
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(uri).apply(options).into(itemimg);
        String name = item.getDownLoadName();
        itemtitle.setText(name);
        String updateTime = item.getMv_update_time();
        if (time.equals(updateTime)) {
            itemTag.setVisibility(View.VISIBLE);
        } else {
            itemTag.setVisibility(View.INVISIBLE);
        }
    }
}
