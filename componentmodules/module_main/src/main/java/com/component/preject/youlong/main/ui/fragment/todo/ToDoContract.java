package com.component.preject.youlong.main.ui.fragment.todo;

import com.component.preject.youlong.base.mvp.BaseModel;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.BaseView;
import com.component.preject.youlong.main.bean.BaseResponse;
import com.component.preject.youlong.main.bean.WxArticleBean;
import com.component.preject.youlong.main.bean.movie.DataBean;
import com.component.preject.youlong.main.bean.movie.MovieResponse;
import com.component.preject.youlong.main.ui.fragment.wxarticle.WxArticleContract;
import io.reactivex.Observable;
import retrofit2.http.Query;

import java.util.List;

/**
 * * @ProjectName:    componentproject
 *
 * @Package: com.component.preject.youlong.main.ui.fragment.todo
 * @ClassName: ToDoContract
 * @Author: xiezhenggen
 * @CreateDate: 2019/1/15 16:00
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/15 16:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public interface ToDoContract {
    interface View extends BaseView {
        void loadBtData(List<DataBean> dataBeanList);
    }

    interface Model extends BaseModel {
        /**
         * 获取推荐
         *
         * @param page
         * @param pagesize
         * @return
         */
        Observable<MovieResponse<List<DataBean>>> getBtRecomend(int page, int pagesize);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        abstract void getWxTabs();

    }
}
