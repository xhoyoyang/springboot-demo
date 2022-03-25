package com.springboot.demo.exception.defination;

import com.springboot.demo.common.result.RsStatus;

public class InfoException extends RuntimeException {

    public InfoException(String msg) {
        super(msg);
    }

    public InfoException() {
        super(RsStatus.OPERATOR_ERROR.getMsg());
    }

    public InfoException(String msg, Throwable t) {
        super(msg, t);
    }

}
