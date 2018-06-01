package com.performance.web.vo;

import java.io.Serializable;

/**
 * DataTable结果集类，用于在页面显示
 * <p/>
 * Created by weixing on 2016/9/14.
 */
public class DataTableVO implements Serializable {
    /**
     * 请求次数计数器，每次发送给服务器后又原封返回。
     * 因为请求是异步的为了确保每次请求能对应到服务器返回的数据。
     * 这里出于安全的考虑，强烈要求把这个转换为整数后再返回，而不是纯粹的接受然后返回。
     * 这是为了防止跨站脚本（XSS）攻击。
     */
    Integer draw;
    /**
     * 即没有过滤的记录数（数据库里总共记录数）
     */
    Long recordsTotal;
    /**
     * 过滤后的记录数（如果有接收到前台的过滤条件，则返回的是过滤后的记录数）
     */
    Long recordsFiltered;
    /**
     * 表格中的实际需要显示的数据
     */
    Object data;
    /**
     * 可选。定义一个错误来描述服务器出了问题后的友好提示。
     */
    String error;

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public DataTableVO setError(String error) {
        this.error = error;
        return this;
    }


    @Override
    public String toString() {
        return "DataTableVO{" +
                "draw=" + draw +
                ", recordsTotal=" + recordsTotal +
                ", recordsFiltered=" + recordsFiltered +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }
}
