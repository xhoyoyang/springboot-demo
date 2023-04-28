package com.springboot.demo.ws.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by huiyang on 2022-07-21
 */
public class WebScoketEvent extends ApplicationEvent {


    private String msg;


    public WebScoketEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
