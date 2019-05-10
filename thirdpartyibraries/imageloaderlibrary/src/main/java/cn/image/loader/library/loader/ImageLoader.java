package cn.image.loader.library.loader;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import cn.image.loader.library.bean.ImageBean;
import cn.image.loader.library.creator.RequestCreator;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @ProjectName: componentproject
 * @Package: cn.image.loader.library.loader
 * @ClassName: ImageLoader
 * @Author: xzg
 * @CreateDate: 2019/5/10 15:15
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/10 15:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class ImageLoader {

    static  ImageLoader singleton;
    private String mUrl;
    private RequestCreator requestCreator;

    //防止用户可以创建该对象
    private  ImageLoader(Builder builder) {
        requestCreator = new RequestCreator(builder.mContext);
    }

    public static  ImageLoader with(Context context) {
        if (singleton == null) {
            synchronized ( ImageLoader.class) {
                if (singleton == null) {
                    singleton = new Builder(context).build();
                }
            }
        }
        return singleton;
    }

    public  ImageLoader load(String url) {
        this.mUrl = url;
        return singleton;
    }

    public void into(final ImageView imageView) {
        Observable
                .concat(
                        requestCreator.getImageFromMemory(mUrl),
                        requestCreator.getImageFromDisk(mUrl),
                        requestCreator.getImageFromNetwork(mUrl)
                )
                .first(new ImageBean(null,mUrl)).toObservable()
                .subscribe(new Observer<ImageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImageBean imageBean) {
                        Log.i("YYYY","imageBean="+imageBean);
                        if (imageBean.getBitmap() != null) {
                            imageView.setImageBitmap(imageBean.getBitmap());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.i("YYYY","imageBean=e="+e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i("YYYY","onComplete=e=");
                        Log.e("onComplete", "onComplete");
                    }
                });
    }

    public static class Builder {

        private Context mContext;

        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public  ImageLoader build() {
            return new  ImageLoader(this);
        }
    }
}
