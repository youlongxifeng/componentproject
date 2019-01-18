package com.component.preject.youlong.main.ui.fragment.movie;

import com.component.preject.youlong.main.bean.movie.DataBean;
import com.component.preject.youlong.main.bean.movie.MovieResponse;
import com.component.preject.youlong.main.http.HttpClientUtils;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ProjectName: componentproject
 * @Package: com.component.preject.youlong.main.ui.fragment.movie
 * @ClassName: MovieModel
 * @Author: xiezhenggen
 * @CreateDate: 2019/1/18 16:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/18 16:39
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class MovieModel implements MovieContract.Model {
    @Override
    public Observable<MovieResponse<List<DataBean>>> getSerisUpdate(int page, int pagesize) {
        return HttpClientUtils.getMovieCommonHttp().getSerisUpdate(page,pagesize);
    }
}
