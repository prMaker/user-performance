package com.performance.web.vo;

import java.io.Serializable;

/**
 * Created by weixing on 2016/9/14.
 */
public class DataTableParam implements Serializable {
    /**
     * 请求次数计数器，每次发送给服务器后又原封返回。
     * 因为请求是异步的为了确保每次请求能对应到服务器返回的数据。
     * 这里出于安全的考虑，强烈要求把这个转换为整数后再返回，而不是纯粹的接受然后返回。
     * 这是为了防止跨站脚本（XSS）攻击。
     */
    private int draw;
    /**
     * 页码
     */
    private int pageNo;
    /**
     * 每页长度
     */
    private int pageSize;
    /**
     * 排序字段
     */
    private String orderField;
    /**
     * 排序方向
     */
    private String orderDir;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
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

    @Override
    public String toString() {
        return "DataTableParam{" +
                "draw=" + draw +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", orderField='" + orderField + '\'' +
                ", orderDir='" + orderDir + '\'' +
                '}';
    }
}
