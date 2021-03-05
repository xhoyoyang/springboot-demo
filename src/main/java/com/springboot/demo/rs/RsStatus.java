package com.springboot.demo.rs;

public enum  RsStatus {



    OK(0,"success"),
    ERROR(-1,"system error"),
    LOGIN_FAIL(1,"login failed"),
    BAD_REQUEST(400,"bad request,required request body is missing"),
    UNAUTHORIZED(401,"Unauthorized"),
    PAYMENT_REQUIRED(402,"Payment Required"),
    FORBIDDEN(403,"Forbidden"),
    NOT_FOUND(404,"Not Found"),
    METHOD_NOT_ALLOWED(404,"Method Not Allowed"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    ;


    private Integer code;
    private String msg;

    RsStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

