package com.component.preject.youlong.main.ui.fragment.todo;

import com.component.preject.youlong.main.bean.movie.DataBean;
import com.component.preject.youlong.main.bean.movie.MovieResponse;
import com.component.preject.youlong.main.http.HttpClientUtils;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ProjectName: componentproject
 * @Package: com.component.preject.youlong.main.ui.fragment.todo
 * @ClassName: ToDoModel
 * @Author: xiezhenggen
 * @CreateDate: 2019/1/16 17:17
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/16 17:17
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class ToDoModel implements ToDoContract.Model {
    @Override
    public Observable<MovieResponse<List<DataBean>>> getBtRecomend(int page, int pagesize) {
        return HttpClientUtils.getMovieCommonHttp().getBtRecomend(page,pagesize);
    }
}
