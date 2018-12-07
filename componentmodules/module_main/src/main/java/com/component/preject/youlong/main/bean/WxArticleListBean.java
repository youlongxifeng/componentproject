package com.component.preject.youlong.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/7 11:23
 * @description: （公众号详情bean）
 */
public class WxArticleListBean implements Serializable {
    private int curPage;
    private int offset;
    private Boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<WxArticleListItemBean>datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Boolean getOver() {
        return over;
    }

    public void setOver(Boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<WxArticleListItemBean> getDatas() {
        return datas;
    }

    public void setDatas(List<WxArticleListItemBean> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "WxArticleListBean{" +
                "curPage=" + curPage +
                ", offset=" + offset +
                ", over=" + over +
                ", pageCount=" + pageCount +
                ", size=" + size +
                ", total=" + total +
                ", datas=" + datas +
                '}';
    }
}
