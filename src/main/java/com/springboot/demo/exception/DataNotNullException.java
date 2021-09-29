package com.springboot.demo.exception;

import com.springboot.demo.rs.RsStatus;

public class DataNotNullException extends RuntimeException{


    public DataNotNullException(String message) {
        super(message);
    }

    public DataNotNullException() {
        super(RsStatus.DATA_NOt_NULL.getMsg());
    }

    public DataNotNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
