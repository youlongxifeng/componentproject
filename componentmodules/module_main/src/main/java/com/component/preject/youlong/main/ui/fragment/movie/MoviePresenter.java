package com.component.preject.youlong.main.ui.fragment.movie;

import com.component.preject.youlong.main.bean.movie.DataBean;
import com.component.preject.youlong.main.bean.movie.MovieResponse;
import com.component.preject.youlong.main.common.Constants;
import com.component.preject.youlong.rx.ApiException;
import com.component.preject.youlong.rx.HttpDisposableObserver;
import com.component.preject.youlong.utils.LogUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

/**
 * @ProjectName: componentproject
 * @Package: com.component.preject.youlong.main.ui.fragment.movie
 * @ClassName: MoviePresenter
 * @Author: xiezhenggen
 * @CreateDate: 2019/1/18 16:40
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/18 16:40
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class MoviePresenter extends MovieContract.Presenter {
    private final static String TAG=MoviePresenter.class.getSimpleName();
    public MoviePresenter(){
        mModel=new MovieModel();
    }
    @Override
    void getSerisUpdate(int page) {
        LogUtils.i(TAG," getDisposableObserver  getWxTabs" );
        HttpDisposableObserver<MovieResponse<List<DataBean>>> httpDisposableObserver=getDisposableObserver(page);
        mModel.getSerisUpdate(1,Constants.PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(httpDisposableObserver);

        addSubscribe(httpDisposableObserver);
    }


    private HttpDisposableObserver<MovieResponse<List<DataBean>>> getDisposableObserver(final int page) {
        return new HttpDisposableObserver<MovieResponse<List<DataBean>>>() {
            @Override
            public void onError(ApiException e) {
                LogUtils.i(TAG,"getDisposableObserver E="+e.getMsg());
            }

            @Override
            public void onNext(MovieResponse<List<DataBean>> listMovieResponse) {

                if(mView!=null){
                    mView.setLoadingSucceeded(page,listMovieResponse);
                }
            }
        };
    }
}
