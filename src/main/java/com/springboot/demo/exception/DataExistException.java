package com.springboot.demo.exception;

public class DataExistException extends RuntimeException {

    public DataExistException(String msg){
        super(msg);
    }

    public DataExistException(String msg , Throwable t){
        super(msg,t);
    }

}
