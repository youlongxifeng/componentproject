package com.component.preject.youlong.main.bean.movie;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: componentproject
 * @Package: com.component.preject.youlong.main.bean.movie
 * @ClassName: MovieResponse
 * @Author: xiezhenggen
 * @CreateDate: 2019/1/16 16:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/16 16:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class MovieResponse<T> implements Serializable {
    private int Code;
    private String Msg;
    private T Data;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "Code=" + Code +
                ", Msg='" + Msg + '\'' +
                ", Data=" + Data +
                '}';
    }
}
