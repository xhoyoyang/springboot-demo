package com.springboot.demo.exception.defination;

import com.springboot.demo.common.result.RsStatus;

public class DataNotExistException extends RuntimeException {

    public DataNotExistException(String msg){
        super(msg);
    }

    public DataNotExistException(){
        super(RsStatus.DATA_NOT_EXIST.getMsg());
    }

    public DataNotExistException(String msg ,Throwable t){
        super(msg,t);
    }

}
