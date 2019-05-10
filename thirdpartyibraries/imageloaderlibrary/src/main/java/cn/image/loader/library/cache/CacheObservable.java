package cn.image.loader.library.cache;


import cn.image.loader.library.bean.ImageBean;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: componentproject
 * @Package: cn.image.loader.library.cache
 * @ClassName: CacheObservable
 * @Author: xzg
 * @CreateDate: 2019/5/10 14:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/10 14:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public abstract class CacheObservable {
    /**
     * 获取缓存数据
     * @param url
     * @return
     */
    public Observable<ImageBean> getImage(final String url) {
        return Observable.create(new ObservableOnSubscribe<ImageBean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<ImageBean> e) throws Exception {
                if (!e.isDisposed()) {
                    ImageBean image = getDataFromCache(url);
                    e.onNext(image);
                    e.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 取出缓存数据
     * @param url
     * @return
     */
    public abstract ImageBean getDataFromCache(String url);

    /**
     * 缓存数据
     * @param image
     */
    public abstract void putDataToCache(ImageBean image);
}
