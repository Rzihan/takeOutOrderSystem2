package com.topview.www.po;

import java.util.Date;

/**
 * @author Pan梓涵
 * 用户实体类：
 *
 */
public class User {

    private int userId;// 主键ID
    private String userLoginId;// 登录ID（手机号）
    private String userPassword;// 密码
    private String userNickname;// 昵称
    private String userEmail;// 邮箱
    private String userHeadportrait;// 头像
    private Date userRegisterTime;// 注册时间
    private Date userLoginTime;// 登录时间
    private String userLoginIp;// 登录IP

    public User() {}

    public User(int userId, String userLoginId, String userPassword, String userNickname, String userEmail,
                String userHeadportrait, Date userRegisterTime, Date userLoginTime, String userLoginIp) {
        this.userId = userId;
        this.userLoginId = userLoginId;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userHeadportrait = userHeadportrait;
        this.userRegisterTime = userRegisterTime;
        this.userLoginTime = userLoginTime;
        this.userLoginIp = userLoginIp;
    }

    public User(String userLoginId, String userPassword, String userNickname, String userEmail) {
        this.userLoginId = userLoginId;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserHeadportrait() {
        return userHeadportrait;
    }

    public void setUserHeadportrait(String userHeadportrait) {
        this.userHeadportrait = userHeadportrait;
    }

    public Date getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(Date userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    public Date getUserLoginTime() {
        return userLoginTime;
    }

    public void setUserLoginTime(Date userLoginTime) {
        this.userLoginTime = userLoginTime;
    }

    public String getUserLoginIp() {
        return userLoginIp;
    }

    public void setUserLoginIp(String userLoginIp) {
        this.userLoginIp = userLoginIp;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userLoginId='" + userLoginId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userHeadportrait='" + userHeadportrait + '\'' +
                '}';
    }
}
