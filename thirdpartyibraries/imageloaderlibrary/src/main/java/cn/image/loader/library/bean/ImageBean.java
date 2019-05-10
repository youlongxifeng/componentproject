package cn.image.loader.library.bean;

import android.graphics.Bitmap;

/**
 * @ProjectName: componentproject
 * @Package: cn.image.loader.library.bean
 * @ClassName: ImageBean
 * @Author: xzg
 * @CreateDate: 2019/5/10 14:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/10 14:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class ImageBean {
    private String url;
    private Bitmap bitmap;
    public ImageBean(Bitmap bitmap, String url) {
        this.bitmap = bitmap;
        this.url = url;
    }
    public Bitmap getBitmap() {
        return bitmap;
    }
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImageBean{" +
                "url='" + url + '\'' +
                ", bitmap=" + bitmap +
                '}';
    }
}
