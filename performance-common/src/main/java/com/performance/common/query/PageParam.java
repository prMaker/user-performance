package com.performance.common.query;

public class PageParam {
    private static final long serialVersionUID = -6791354809080761535L;


    public final static int NO_ROW_OFFSET = 0;
    public final static int NO_ROW_LIMIT = 20;

    /**
     * 偏移量
     */
    private long offset;
    /**
     * 每页数量
     */
    private long limit;
    /**
     * 页号
     */
    protected long pageNum = 1;
    /**
     * 页大小
     */
    protected int pageSize = 20;


    public PageParam() {
        this.offset = NO_ROW_OFFSET;
        this.limit = NO_ROW_LIMIT;
    }


    public PageParam(long offset, long limit) {
        this.offset = offset;
        this.limit = limit;
    }


    public long getOffset() {
        return offset;
    }


    public void setOffset(long offset) {
        this.offset = offset;
    }


    public long getLimit() {
        return limit;
    }


    public void setLimit(long limit) {
        this.limit = limit;
    }


    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        if (pageNum <= 0) {
            this.pageNum = 1;
        } else {
            this.pageNum = pageNum;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize <= 0) {
            this.pageSize = 20;
        } else {
            this.pageSize = pageSize;
            this.setOffset(this.pageSize * this.pageNum + 1);
            this.setLimit(this.pageSize);
        }
    }
}
