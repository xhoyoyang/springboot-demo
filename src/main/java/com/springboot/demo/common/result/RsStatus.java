package com.springboot.demo.common.result;

public enum RsStatus {


    OK(0, "success"),
    LOGIN_FAIL(1, "login failed,please check the account or password"),
    Bad_Credentials(2, "Bad Credentials"),
    DATA_NOT_EXIST(3, "Data not Exist"),
    DATA_EXIST(4, "Data is exist"),
    DATA_NOt_NULL(5, "Data can not be null"),
    OPERATOR_ERROR(6, "操作失败，请稍微再试"),
    BAD_REQUEST(400, "bad request,required request body is missing"),
    UNAUTHORIZED(401, "Unauthorized"),
    PAYMENT_REQUIRED(402, "Payment Required"),
    FORBIDDEN(403, "Access Denied"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(404, "Method Not Allowed"),
    ERROR(500, "Internal Server Error"),
    ;


    private final Integer code;
    private final String msg;

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

