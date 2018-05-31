package com.performance.pojo.constant;

public enum DispositionEnum {

    Admin(99, "管理员"),
    OneLevel(1, "普通员工"),
    TwoLevel(2, "负责人"),
    ThreeLevel(3, "中心经理"),
    FourLevel(4, "部门经理");

    private int code;
    private String desc;

    private DispositionEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
