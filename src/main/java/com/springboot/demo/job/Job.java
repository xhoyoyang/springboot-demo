package com.springboot.demo.job;

import com.springboot.demo.ws.WebSocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by huiyang on 2022-07-21
 */
@Component
@Slf4j
public class Job {


    @Resource
    private WebSocketHandler webSocketHandler;


    /**
     * websocket广播消息
     */
    @Scheduled(fixedDelay = 10000L, initialDelay = 5000L)
    public void socketMessage() {
        //this.webSocketHandler.sendAllMessage("weocket server 广播消息，测试客户端是否连接");
    }


}
