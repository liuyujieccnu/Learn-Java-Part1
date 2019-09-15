package com.example.springbootwebsocket.config;

import com.example.springbootwebsocket.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling //调度程序，用以将消息推送到broker
@Configuration
public class SchedulerConfig {

    @Autowired
    SimpMessagingTemplate template;//创建简单的消息模板来转换消息

    @Scheduled(fixedDelay = 5000)//定义调度的时间延迟
    public void sendAdhocMessages(){
        template.convertAndSend("topic/user",new UserResponse("Scheduler"));
    }

}
