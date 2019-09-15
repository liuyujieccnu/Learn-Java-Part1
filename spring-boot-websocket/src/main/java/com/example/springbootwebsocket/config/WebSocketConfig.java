package com.example.springbootwebsocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration //告诉Spring-boot这是配置相关子文件
@EnableWebSocketMessageBroker //配置的内容
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { //创建webSocket
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){ //configure the message broker,enable an in-memory message broker to carry the messges back to client on destination.
        //在这里色值broker的信息，添加一个目标prefix（前缀），消息选择正确的channel
        //A message broker is a computer program that transmit a message for a sender to receiver.
        config.enableSimpleBroker("/topic");//在topic域上可以向客户端发消息
        config.setApplicationDestinationPrefixes("/app");//客户单向服务器端发送时的主题上面需要加"/app"作为前缀
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {//register the endpoint
        registry.addEndpoint("/user");
        registry.addEndpoint("/user").withSockJS();//创建一个端点
    }
}
