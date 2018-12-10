package com.component.project.youlong;

import org.greenrobot.greendao.annotation.*;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/7 17:02
 * @description: （添加一句描述）
 */
@Entity(nameInDb = "UserBean", indexes = {@Index(value = "_id DESC", unique = true)})
public class UserBean {
    /**
     * 主键自增 这里需要注意，如果要实现自增，id必须是Long,为long不行！
     */
    @Id(autoincrement = true)
    private Long _id;
    /**
     * 用户ID
     */
    @NotNull
    @Property(nameInDb = "userid")
    private String userid;
    /* *//**
     * 用户账号（当前只支持手机号码)
     *//*
    @NotNull
    private String username;
    *//**
     * 用户密码
     *//*
    @NotNull
    private String password;*/
    @NotNull
    @Property(nameInDb = "token")
    private String accessToken;
    @Generated(hash = 1953966444)
    public UserBean(Long _id, @NotNull String userid, @NotNull String accessToken) {
        this._id = _id;
        this.userid = userid;
        this.accessToken = accessToken;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getUserid() {
        return this.userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getAccessToken() {
        return this.accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
