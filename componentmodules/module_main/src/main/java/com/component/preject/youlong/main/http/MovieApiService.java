package com.component.preject.youlong.main.http;

import com.component.preject.youlong.main.bean.movie.DataBean;
import com.component.preject.youlong.main.bean.movie.MovieResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * @ProjectName: componentproject
 * @Package: com.component.preject.youlong.main.http
 * @ClassName: MovieApiService
 * @Author: xiezhenggen
 * @CreateDate: 2019/1/16 17:13
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/16 17:13
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public interface MovieApiService {
    @GET("ygcms/getClassVideo.php")
    Observable<MovieResponse<List<DataBean>>> getBtRecomend(@Query("page") int page, @Query("pagesize") int pagesize);//获取推荐
}
