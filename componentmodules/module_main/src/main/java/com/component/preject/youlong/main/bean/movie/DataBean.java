package com.component.preject.youlong.main.bean.movie;

import java.io.Serializable;

/**
 * @ProjectName: componentproject
 * @Package: com.component.preject.youlong.main.bean
 * @ClassName: DataBean
 * @Author: xiezhenggen
 * @CreateDate: 2019/1/16 16:26
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/16 16:26
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class DataBean implements Serializable {
    private String movClass;
    private String downLoadName;
    private String downLoadUrl;
    private String mvdesc;
    private String downimgurl;
    private String mv_update_time;
    private String downdtitle;
    private String id;
    private String mv_md5_id;

    public String getMovClass() {
        return movClass;
    }

    public void setMovClass(String movClass) {
        this.movClass = movClass;
    }

    public String getDownLoadName() {
        return downLoadName;
    }

    public void setDownLoadName(String downLoadName) {
        this.downLoadName = downLoadName;
    }

    public String getDownLoadUrl() {
        return downLoadUrl;
    }

    public void setDownLoadUrl(String downLoadUrl) {
        this.downLoadUrl = downLoadUrl;
    }

    public String getMvdesc() {
        return mvdesc;
    }

    public void setMvdesc(String mvdesc) {
        this.mvdesc = mvdesc;
    }

    public String getDownimgurl() {
        return downimgurl;
    }

    public void setDownimgurl(String downimgurl) {
        this.downimgurl = downimgurl;
    }

    public String getMv_update_time() {
        return mv_update_time;
    }

    public void setMv_update_time(String mv_update_time) {
        this.mv_update_time = mv_update_time;
    }

    public String getDowndtitle() {
        return downdtitle;
    }

    public void setDowndtitle(String downdtitle) {
        this.downdtitle = downdtitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMv_md5_id() {
        return mv_md5_id;
    }

    public void setMv_md5_id(String mv_md5_id) {
        this.mv_md5_id = mv_md5_id;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "movClass='" + movClass + '\'' +
                ", downLoadName='" + downLoadName + '\'' +
                ", downLoadUrl='" + downLoadUrl + '\'' +
                ", mvdesc='" + mvdesc + '\'' +
                ", downimgurl='" + downimgurl + '\'' +
                ", mv_update_time='" + mv_update_time + '\'' +
                ", downdtitle='" + downdtitle + '\'' +
                ", id='" + id + '\'' +
                ", mv_md5_id='" + mv_md5_id + '\'' +
                '}';
    }
}
