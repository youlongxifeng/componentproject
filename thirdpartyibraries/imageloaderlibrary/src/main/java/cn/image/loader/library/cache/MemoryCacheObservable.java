package cn.image.loader.library.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;
import cn.image.loader.library.bean.ImageBean;

/**
 * @ProjectName: componentproject
 * @Package: cn.image.loader.library.cache
 * @ClassName: MemoryCacheObservable
 * @Author: xzg
 * @CreateDate: 2019/5/10 15:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/10 15:12
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class MemoryCacheObservable  extends CacheObservable {

    private int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    private int cacheSize = maxMemory / 4;
    private LruCache<String, Bitmap> mLruCache = new LruCache<String, Bitmap>(cacheSize) {
        @Override
        protected int sizeOf(String key, Bitmap bitmap) {
            return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
        }
    };

    @Override
    public ImageBean getDataFromCache(String url) {
        Log.e("getDataFromCache", "getDataFromMemoryCache");
        Bitmap bitmap = mLruCache.get(url);
        return new ImageBean(bitmap, url);
    }

    @Override
    public void putDataToCache(ImageBean image) {
        mLruCache.put(image.getUrl(), image.getBitmap());
    }
}
