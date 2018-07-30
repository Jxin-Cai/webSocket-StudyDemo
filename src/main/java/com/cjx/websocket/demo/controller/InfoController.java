package com.cjx.websocket.demo.controller;

import com.cjx.websocket.demo.model.InMessage;
import com.cjx.websocket.demo.model.OutMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

/**
 * @author:蔡佳新
 * @date:创建时间 18:08 2018/7/30
 * @note:
 */
@Controller
public class InfoController {

    @MessageMapping("/v1/chat")
    @SendTo("/topic/chat")
    public OutMessage info(InMessage message){

        return OutMessage.builder()
                .content(message.getContent())
                .origin(message.getOrigin())
                .time(LocalDateTime.now())
                .build();
    }
}
