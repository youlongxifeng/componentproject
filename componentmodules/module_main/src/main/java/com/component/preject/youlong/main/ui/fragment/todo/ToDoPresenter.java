package com.component.preject.youlong.main.ui.fragment.todo;

import com.component.preject.youlong.main.bean.WxArticleBean;
import com.component.preject.youlong.main.bean.movie.DataBean;
import com.component.preject.youlong.main.bean.movie.MovieResponse;
import com.component.preject.youlong.main.rx.MainRxSchedulers;
import com.component.preject.youlong.rx.ApiException;
import com.component.preject.youlong.rx.HttpDisposableObserver;
import com.component.preject.youlong.utils.LogUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

/**
 * @ProjectName: componentproject
 * @Package: com.component.preject.youlong.main.ui.fragment.todo
 * @ClassName: ToDoPresenter
 * @Author: xiezhenggen
 * @CreateDate: 2019/1/16 17:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/16 17:11
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class ToDoPresenter extends ToDoContract.Presenter {
    private final static String TAG=ToDoPresenter.class.getSimpleName();
    public ToDoPresenter(){
        mModel=new ToDoModel();
    }
    @Override
    void getWxTabs() {
        HttpDisposableObserver<MovieResponse<List<DataBean>>> httpDisposableObserver=getDisposableObserver();
        mModel.getBtRecomend(1,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(httpDisposableObserver);

        addSubscribe(httpDisposableObserver);
    }

    private HttpDisposableObserver<MovieResponse<List<DataBean>>> getDisposableObserver() {
        return new HttpDisposableObserver<MovieResponse<List<DataBean>>>() {
            @Override
            public void onError(ApiException e) {
                LogUtils.i(TAG,"getDisposableObserver E="+e.getMsg());
            }

            @Override
            public void onNext(MovieResponse<List<DataBean>> listMovieResponse) {
                LogUtils.i(TAG,"getDisposableObserver listMovieResponse="+listMovieResponse);
                if(mView!=null){
                    mView.loadBtData(listMovieResponse.getData());
                }
            }
        };
    }
}
