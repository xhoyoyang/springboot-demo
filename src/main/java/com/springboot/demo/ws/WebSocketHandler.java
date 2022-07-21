package com.springboot.demo.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by huiyang on 2022-07-21
 */
@Service
@Slf4j
public class WebSocketHandler {

    /**
     * 所有用户的连接
     */
    private static ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();

    /**
     * 新增用户连接
     *
     * @param sessionId
     * @param session
     */
    public static void addSession(String sessionId, Session session) {

        sessions.put(sessionId, session);
    }

    /**
     * 新增用户连接
     *
     * @param sessionId
     */
    public static void deleteSession(String sessionId) {

        sessions.remove(sessionId);
    }

    public static String getSessionId(String id, String type) {
        return id + type;
    }

    /**
     * 获取所有的连接
     *
     * @return
     */
    public ConcurrentHashMap<String, Session> getSessions() {
        return sessions;
    }


    /**
     * 广播消息
     *
     * @param message
     */
    public void sendAllMessage(String message) {
        log.info("socket广播消息:" + message);
        for (String id : sessions.keySet()) {
            try {
                sessions.get(id).getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.info("socket广播消息失败，id：{}，message：{}", id, message);
            }
        }
    }


}
