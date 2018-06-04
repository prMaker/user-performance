package com.performance.common.query;

public class UserLoginPageParam extends PageParam {

    /**
     * 页号
     */
    private long pageNum = 1;
    /**
     * 页大小
     */
    private int pageSize = 20;
    /**
     * 排序字段
     */
    private String orderField;
    /**
     * 排序方向
     */
    private String orderDir;

    /**
     * 用户登录信息表ID
     */
    private Long createdUserId;

    /**
     * 修改用户Id
     */
    private Long modifiedUserId;

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderDir() {
        return orderDir;
    }

    public void setOrderDir(String orderDir) {
        this.orderDir = orderDir;
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
        return "UserLoginPageParam{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", orderField='" + orderField + '\'' +
                ", orderDir='" + orderDir + '\'' +
                ", createdUserId=" + createdUserId +
                ", modifiedUserId=" + modifiedUserId +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
