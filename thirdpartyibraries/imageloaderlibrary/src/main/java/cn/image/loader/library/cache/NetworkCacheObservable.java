package cn.image.loader.library.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import cn.image.loader.library.bean.ImageBean;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
/**
 * @ProjectName: componentproject
 * @Package: cn.image.loader.library.cache
 * @ClassName: NetworkCacheObservable
 * @Author: xzg
 * @CreateDate: 2019/5/10 14:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/10 14:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class NetworkCacheObservable  extends CacheObservable {
    @Override
    public ImageBean getDataFromCache(String url) {
        Log.e("getDataFromCache", "getDataFromNetworkCache");
        Bitmap bitmap = downloadImage(url);
        return new ImageBean(bitmap, url);
    }

    @Override
    public void putDataToCache(ImageBean image) {

    }

    /**
     * 下载文件
     * @param url
     * @return
     */
    public Bitmap downloadImage(String url) {
        Bitmap bitmap = null;
        InputStream inputStream = null;
        try {
            URL imageUrl = new URL(url);
            URLConnection urlConnection = (HttpURLConnection) imageUrl.openConnection();
            inputStream = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }
}

