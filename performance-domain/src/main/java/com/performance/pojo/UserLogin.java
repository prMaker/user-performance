package com.performance.pojo;

import java.sql.Timestamp;

public class UserLogin {

    protected Long loginId;
    protected Long userInfoId;
    protected Integer isDeleted;
    protected String loginName;
    protected String password;
    protected Integer dispostion;
    protected Timestamp createdTime;
    protected Timestamp modifiedTime;
    protected String createdUser;
    protected String modifiedUser;
    //TODO 是否添加
    protected Long createdUserId;// 创建人用户ID
    protected Long modifiedUserId;// 修改人用户ID

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDispostion() {
        return dispostion;
    }

    public void setDispostion(Integer dispostion) {
        this.dispostion = dispostion;
    }


    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Long getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Long createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Long getModifiedUserId() {
        return modifiedUserId;
    }

    public void setModifiedUserId(Long modifiedUserId) {
        this.modifiedUserId = modifiedUserId;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "loginId=" + loginId +
                ", userInfoId=" + userInfoId +
                ", isDeleted=" + isDeleted +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", dispostion=" + dispostion +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                ", createdUser='" + createdUser + '\'' +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", createdUserId=" + createdUserId +
                ", modifiedUserId=" + modifiedUserId +
                '}';
    }
}
