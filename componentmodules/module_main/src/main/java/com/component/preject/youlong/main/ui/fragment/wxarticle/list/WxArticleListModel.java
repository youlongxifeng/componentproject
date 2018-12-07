package com.component.preject.youlong.main.ui.fragment.wxarticle.list;

import com.component.preject.youlong.main.bean.BaseResponse;
import com.component.preject.youlong.main.bean.WxArticleBean;
import com.component.preject.youlong.main.bean.WxArticleListBean;
import com.component.preject.youlong.main.http.HttpClientUtils;
import io.reactivex.Observable;
import retrofit2.http.Path;

import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/7 11:43
 * @description: （添加一句描述）
 */
public class WxArticleListModel  implements WxArticleListContract.Model{
    @Override
    public Observable<BaseResponse<WxArticleListBean>> getWxList(int id ,   int page ) {
        return  HttpClientUtils.getCommonHttp().getWxList(id,page);
    }
}
