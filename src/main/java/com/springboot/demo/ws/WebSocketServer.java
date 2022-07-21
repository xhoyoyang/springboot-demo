package com.springboot.demo.ws;

import com.springboot.demo.ws.event.WebScoketEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by huiyang on 2022-07-20
 */
@Component
@ServerEndpoint("/ws/{id}/{type}")
@Slf4j
public class WebSocketServer {

    private static ApplicationEventPublisher applicationEventPublisher;


    @Autowired
    public void setApplicationEventPublisherApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        WebSocketServer.applicationEventPublisher = applicationEventPublisher;
    }

    @OnOpen
    public void OnOpen(Session session, @PathParam("id") String id, @PathParam("type") String type) {

        log.info("有新的用户建立了socket连接，id：{}，type：{}", id, type);
        WebSocketHandler.addSession(WebSocketHandler.getSessionId(id, type), session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("id") String id, @PathParam("type") String type) {
        log.info("用户关闭了连接，id：{}", id);
        WebSocketHandler.deleteSession(WebSocketHandler.getSessionId(id, type));
    }

    @OnMessage
    public void onMessage(@PathParam("id") String id, String msg) {
        log.error("收到新的客户端推送的消息：id：{}，message：{}", id, msg);
        applicationEventPublisher.publishEvent(new WebScoketEvent(this, msg));
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("socket发送错误：{}", error.getMessage(), error);
    }


}
