package com.performance.service.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 错误级别很高时抛出此异常
 */
public class ErrorDataException extends RuntimeException {
    private static final Logger _logger = LoggerFactory.getLogger(ErrorDataException.class);

    public ErrorDataException(){super();}

    public ErrorDataException(String msg){super(msg);}

    public ErrorDataException(String msg, Throwable t){
        super(msg,t);
        _logger.error("数据查询异常：" + msg, t);
    }
}
