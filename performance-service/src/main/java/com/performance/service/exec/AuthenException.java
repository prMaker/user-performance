package com.performance.service.exec;

public class AuthenException extends RuntimeException{

    public AuthenException(){super();}

    public AuthenException(String msg){
        super(msg);
    }
}
