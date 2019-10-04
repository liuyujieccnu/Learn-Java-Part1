package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

//一个websocket的服务器端实例

@Component
//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。
@ServerEndpoint("/chat/{username}")//可以向服务器端传递参数
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    // 保存所有客户端的链接会话
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();//所有在线的客户端
    private static HashMap<String, String> users = new HashMap<>();//所有用户

    private static void sendMessageToAll(String msg) {
        //TODO: add send message method.
        onlineSessions.forEach((id, session) -> {
            session.getAsyncRemote().sendText(msg);//循环在线的onlineSessions的session，向每个用户发消息
            //getAsyncRemote()为消息推送函数，getAsyncRemote是非阻塞式的，getBasicRemote是阻塞式的
        });

    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String user) {
        //TODO: add on open connection.

        // 在开起一个会话时需要完成
        onlineSessions.put(session.getId(), session); //将本次链接会话以"ID-session"的形式保存
        users.put(session.getId(), user);//根据id向用户表添加用户
        Message message = new Message(user, "ENTERED THE CHAT", onlineSessions.size(), "ENTER");//构建向所有用户发送的message消息，并更新在线session的数量
        sendMessageToAll(JSON.toJSONString(message));//完成向所有用户发送信息
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //TODO: add send message.
        Message message = JSON.parseObject(jsonStr, Message.class);//把客户端传来的Json格式的数据转换成message对象
        Message messageSend = new Message(message.getUsername(), message.getContent(), onlineSessions.size(), "SPEAK");
        sendMessageToAll(JSON.toJSONString(messageSend));
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        //TODO: add close connection.
        onlineSessions.remove(session.getId());
        Message message = new Message(users.get(session.getId()), "LEAVE THE CHAT", onlineSessions.size(), "LEAVE");
        users.remove(session.getId());
        sendMessageToAll(JSON.toJSONString(message));
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
