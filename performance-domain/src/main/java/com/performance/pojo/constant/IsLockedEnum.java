package com.performance.pojo.constant;

public enum IsLockedEnum {

    IsLocked(1),// 已经删除
    IsNotLocked(0);// 未删除

    private int code;

    private IsLockedEnum(int code){
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
