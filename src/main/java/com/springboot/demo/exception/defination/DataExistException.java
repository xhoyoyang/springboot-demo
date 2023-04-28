package com.springboot.demo.exception.defination;

import com.springboot.demo.common.result.RsStatus;

public class DataExistException extends RuntimeException {

    public DataExistException(String msg) {
        super(msg);
    }

    public DataExistException() {
        super(RsStatus.DATA_EXIST.getMsg());
    }

    public DataExistException(String msg, Throwable t) {
        super(msg, t);
    }

}
