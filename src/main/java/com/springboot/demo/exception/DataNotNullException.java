package com.springboot.demo.exception;

public class DataNotNullException extends RuntimeException{


    public DataNotNullException(String message) {
        super(message);
    }

    public DataNotNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
