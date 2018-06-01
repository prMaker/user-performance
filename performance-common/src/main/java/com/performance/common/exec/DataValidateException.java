package com.performance.common.exec;

public class DataValidateException extends RuntimeException{
    public DataValidateException(){}
    public DataValidateException(String msg){super(msg);}
    public DataValidateException(String msg, Throwable t){super(msg, t);}
}
