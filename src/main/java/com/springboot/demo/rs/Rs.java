package com.springboot.demo.rs;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class Rs<T> implements Serializable {

    private Integer code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Rs(){

    }

    public Rs(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Rs(RsStatus status){
        this.code=status.getCode();
        this.msg=status.getMsg();
    }

    public static Rs init(Integer code ,String msg){
        Rs rs= new Rs(code,msg);
        return rs;
    }

    public static Rs ok(){
        return init(RsStatus.OK.getCode(),RsStatus.OK.getMsg());
    }

    public static <T> Rs<T> ok(T data){
        return Rs.ok().setData(data);
    }

    public static Rs error(){
        return init(RsStatus.ERROR.getCode(),RsStatus.ERROR.getMsg());
    }

    public static Rs error(RsStatus status){
        return init(status.getCode(),status.getMsg());
    }


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Rs<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Rs<T> setData(T data) {
        this.data = data;
        return this;
    }
}
