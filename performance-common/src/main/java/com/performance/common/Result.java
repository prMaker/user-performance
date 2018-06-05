package com.performance.common;

public class Result {

    private boolean success;
    private String msg;
    private Object data;
    public Result(){
        this.success = true;
    }

    public Result(boolean success, String msg){
        this.success = success;
        this.msg = msg;
    }

    public Result(boolean success, String msg, Object data){
        this(success, msg);
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
