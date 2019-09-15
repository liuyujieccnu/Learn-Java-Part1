package com.example.springbootwebsocket.controller;

import com.example.springbootwebsocket.model.User;
import com.example.springbootwebsocket.model.UserResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class UserController {

    @MessageMapping("/user")   //映射消息到user
    @SendTo("/topic/user")     //发送消息的目标位置，发送消息到broker
    public UserResponse getUser(User user) throws Exception{   //消息名为getUser，UserResponse为massive wrapper
        return new UserResponse(HtmlUtils.htmlEscape(user.getName()));
    }

}
