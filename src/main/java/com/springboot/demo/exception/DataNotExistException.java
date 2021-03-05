package com.springboot.demo.exception;

public class DataNotExistException extends RuntimeException {

    public DataNotExistException(String msg){
        super(msg);
    }

    public DataNotExistException(String msg ,Throwable t){
        super(msg,t);
    }

}
