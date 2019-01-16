package com.component.preject.youlong.main.ui.fragment.todo.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.component.preject.youlong.base.BaseApplication;
import com.component.preject.youlong.main.R;
import com.component.preject.youlong.main.bean.movie.DataBean;
import com.component.preject.youlong.main.view.PosterItemView;

import java.util.List;

import static com.component.preject.youlong.main.R2.*;

/**
 * @ProjectName: componentproject
 * @Package: com.component.preject.youlong.main.ui.fragment.todo.adapter
 * @ClassName: BannerAdapter
 * @Author: xiezhenggen
 * @CreateDate: 2019/1/16 16:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/16 16:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class BannerAdapter extends BaseQuickAdapter<DataBean, BannerHolder> {

    public BannerAdapter(int layoutResId, @Nullable List<DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BannerHolder helper, DataBean info) {
        TextView tvAuthor = helper.getView(R.id.movtitle);
        String url=info.getDownimgurl().split(",")[0];
        PosterItemView posterItemView=helper.getView(R.id.banner);
        tvAuthor.setText(info.getDownLoadName());


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

        Glide.with(BaseApplication.getContext())
                .load(url)
                .apply(options)
                .into(posterItemView);

    }

}

