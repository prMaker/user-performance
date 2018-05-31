package com.performance.common.query;

public class PageParam {
    private static final long serialVersionUID = -6791354809080761535L;


    public final static int NO_ROW_OFFSET = 0;
    public final static int NO_ROW_LIMIT = 20;

    private long offset;
    private long limit;


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
}
