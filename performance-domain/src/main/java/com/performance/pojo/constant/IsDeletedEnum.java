package com.performance.pojo.constant;

public enum IsDeletedEnum {

    IsDeleted(1),// 已经删除
    IsNotDeleted(0);// 未删除

    private int code;

    private IsDeletedEnum(int code){
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
