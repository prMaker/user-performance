package com.performance.common.query;

public class UserInfoPageParam extends PageParam {
    /**
     * 审核时间
     */
    private String performanceTime;
    /**
     * 排序字段
     */
    private String orderField;
    /**
     * 排序方向
     */
    private String orderDir;
    /**
     * 父Id
     */
    private Long pid;


    public String getPerformanceTime() {
        return performanceTime;
    }

    public void setPerformanceTime(String performanceTime) {
        this.performanceTime = performanceTime;
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

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getPid() {
        return pid;
    }

    @Override
    public String toString() {
        return "UserInfoPageParam{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", performanceTime='" + performanceTime + '\'' +
                ", orderField='" + orderField + '\'' +
                ", orderDir='" + orderDir + '\'' +
                ", pid=" + pid +
                '}';
    }
}
