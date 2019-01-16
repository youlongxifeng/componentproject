package com.component.preject.youlong.main.ui.fragment.todo.adapter;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.component.preject.youlong.main.R2;
import com.component.preject.youlong.main.view.PosterItemView;

/**
 * @ProjectName: componentproject
 * @Package: com.component.preject.youlong.main.ui.fragment.todo.adapter
 * @ClassName: BannerHolder
 * @Author: xiezhenggen
 * @CreateDate: 2019/1/16 16:28
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/16 16:28
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class BannerHolder  extends BaseViewHolder {
    @BindView(R2.id.banner)
    public PosterItemView iv;
    @BindView(R2.id.movtitle)
    public TextView title;
    public BannerHolder(View view) {
        super(view);
    }
}
