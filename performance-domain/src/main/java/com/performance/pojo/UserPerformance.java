package com.performance.pojo;

import java.sql.Timestamp;

public class UserPerformance {

    private Long performanceId;
    private Long userInfoId;
    private Integer isDeleted;
    private String performanceContent;
    private Integer performanceScore;
    private Long operateUserInfoId;
    private Timestamp createdTime;
    private Timestamp modifiedTime;
    private Integer isLocked;
    private Integer operateDisposition;
    private String performanceTime;

    public Long getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Long performanceId) {
        this.performanceId = performanceId;
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

    public Integer getPerformanceScore() {
        return performanceScore;
    }

    public void setPerformanceScore(Integer performanceScore) {
        this.performanceScore = performanceScore;
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

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    public Integer getOperateDisposition() {
        return operateDisposition;
    }

    public void setOperateDisposition(Integer operateDisposition) {
        this.operateDisposition = operateDisposition;
    }

    public String getPerformanceTime() {
        return performanceTime;
    }

    public void setPerformanceTime(String performanceTime) {
        this.performanceTime = performanceTime;
    }

    public String getPerformanceContent() {
        return performanceContent;
    }

    public void setPerformanceContent(String performanceContent) {
        this.performanceContent = performanceContent;
    }

    public Long getOperateUserInfoId() {
        return operateUserInfoId;
    }

    public void setOperateUserInfoId(Long operateUserInfoId) {
        this.operateUserInfoId = operateUserInfoId;
    }

    @Override
    public String toString() {
        return "UserPerformance{" +
                "performanceId=" + performanceId +
                ", userInfoId=" + userInfoId +
                ", isDeleted=" + isDeleted +
                ", performanceContent='" + performanceContent + '\'' +
                ", performanceScore=" + performanceScore +
                ", operateUserInfoId=" + operateUserInfoId +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                ", isLocked=" + isLocked +
                ", operateDisposition=" + operateDisposition +
                ", performanceTime='" + performanceTime + '\'' +
                '}';
    }
}
