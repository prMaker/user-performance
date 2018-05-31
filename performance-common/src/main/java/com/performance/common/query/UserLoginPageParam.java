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

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

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
}
