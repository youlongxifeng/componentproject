package com.component.preject.youlong.main.ui.fragment.movie;

import com.component.preject.youlong.base.mvp.BaseModel;
import com.component.preject.youlong.base.mvp.BasePresenter;
import com.component.preject.youlong.base.mvp.BaseView;
import com.component.preject.youlong.main.bean.movie.DataBean;
import com.component.preject.youlong.main.bean.movie.MovieResponse;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ProjectName: componentproject
 * @Package: com.component.preject.youlong.main.ui.fragment.movie
 * @ClassName: MovieContract
 * @Author: xiezhenggen
 * @CreateDate: 2019/1/18 16:32
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/18 16:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public interface MovieContract {
    interface View extends BaseView {
        void setLoadingSucceeded(int pagecount,MovieResponse<List<DataBean>> wxArticleBeanList);
        void recoveryBoxListIsEmpty(int pagecount);
        void setLoadingFail(int pagecount,String fail);
    }

    interface Model extends BaseModel {
        Observable<MovieResponse<List<DataBean>>> getSerisUpdate(int page, int pagesize);

    }

    abstract class Presenter extends BasePresenter<View,Model> {
        abstract void getSerisUpdate(int page);

    }
}
