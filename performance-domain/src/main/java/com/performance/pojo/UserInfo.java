package com.performance.pojo;

import java.sql.Timestamp;

public class UserInfo {

    public static final Long TOTAL_PID = 0L;

    protected Long userInfoId;
    protected Long loginId;
    protected String idCard;
    protected String userName;
    protected String birthday;
    protected Integer sex;
    protected String phone;
    protected Integer isDeleted;
    protected Long pid;
    protected Integer dispostion;
    protected Timestamp createdTime;
    protected Timestamp modifiedTime;
    protected Long createdUserInfoId;
    protected Long modifiedUserInfoId;

    /////////// get set //////////
    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
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

    public Long getCreatedUserInfoId() {
        return createdUserInfoId;
    }

    public void setCreatedUserInfoId(Long createdUserInfoId) {
        this.createdUserInfoId = createdUserInfoId;
    }

    public Long getModifiedUserInfoId() {
        return modifiedUserInfoId;
    }

    public void setModifiedUserInfoId(Long modifiedUserInfoId) {
        this.modifiedUserInfoId = modifiedUserInfoId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userInfoId=" + userInfoId +
                ", loginId=" + loginId +
                ", idCard='" + idCard + '\'' +
                ", userName='" + userName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", isDeleted=" + isDeleted +
                ", pid=" + pid +
                ", dispostion=" + dispostion +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                ", createdUserInfoId=" + createdUserInfoId +
                ", modifiedUserInfoId=" + modifiedUserInfoId +
                '}';
    }
}
