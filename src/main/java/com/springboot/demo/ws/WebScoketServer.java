package com.springboot.demo.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by huiyang on 2022-07-20
 */
@Component
@ServerEndpoint("/ws")
@Slf4j
public class WebScoketServer {

    @OnOpen
    public void OnOpen(Session session, @PathParam("id") String id) {

        log.info("new user to connect to websocket:{}-{}", session.getId(), id);
    }

    @OnClose
    public void onClose(Session session, @PathParam("id") String id) {

        log.info("user to close to websocket:{}-{}", session.getId(), id);
    }

    @OnMessage
    public void onMessage(Session session, String msg) {

        log.error("received client message，clinet：{}，message：{}", session.getId(), msg);
    }

    @OnError
    public void onError(Session session, Throwable error) {

        log.error(error.getMessage(), error);
    }


}
