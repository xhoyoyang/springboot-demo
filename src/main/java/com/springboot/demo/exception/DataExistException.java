package com.springboot.demo.exception;

import com.springboot.demo.rs.RsStatus;

public class DataExistException extends RuntimeException {

    public DataExistException(String msg){
        super(msg);
    }

    public DataExistException(){
        super(RsStatus.DATA_EXIST.getMsg());
    }

    public DataExistException(String msg , Throwable t){
        super(msg,t);
    }

}
