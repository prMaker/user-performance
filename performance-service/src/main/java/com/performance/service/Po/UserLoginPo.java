package com.performance.service.Po;

import com.performance.pojo.UserInfo;
import com.performance.pojo.UserLogin;

public class UserLoginPo extends UserLogin {
    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "UserLoinPo{" +
                "userInfo=" + userInfo +
                ", loginId=" + loginId +
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
