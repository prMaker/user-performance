package com.performance.service.Po;

import com.performance.pojo.UserInfo;
import com.performance.pojo.UserPerformance;

public class UserInfoPerfor extends UserInfo {

    private UserPerformance userPerformance;

    private boolean PermissionToFix;// 是否可以修改绩效

    public UserPerformance getUserPerformance() {
        return userPerformance;
    }

    public void setUserPerformance(UserPerformance userPerformance) {
        this.userPerformance = userPerformance;
    }

    public boolean isPermissionToFix() {
        return PermissionToFix;
    }

    public void setPermissionToFix(boolean permissionToFix) {
        PermissionToFix = permissionToFix;
    }

    @Override
    public String toString() {
        return "UserInfoPerfor{" +
                "userPerformance=" + userPerformance +
                ", PermissionToFix=" + PermissionToFix +
                ", userInfoId=" + userInfoId +
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
