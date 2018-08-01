package com.cjx.websocket.demo.controller;

import com.cjx.websocket.demo.model.InMessage;
import com.cjx.websocket.demo.model.OutMessage;
import com.cjx.websocket.demo.ws.WebsocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

/**
 * @author:蔡佳新
 * @date:创建时间 18:08 2018/7/30
 * @note:
 */
@Controller
public class InfoController {
    @Autowired
    private WebsocketService ws;

    //demo1
    @MessageMapping("/v1/chat")
    @SendTo("/topic/chat")
    public OutMessage infoV1(InMessage message){

        return OutMessage.builder()
                .content(message.getContent())
                .origin(message.getOrigin())
                .time(LocalDateTime.now())
                .build();
    }

    //demo2
    @MessageMapping("/v2/chat")
    public void infoV2(InMessage message){

        try {
            ws.sendTopicToAll("/topic/rank",message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //demo3
    @MessageMapping("/v3/single/chat")
    public void singleChat(InMessage message) {

        ws.sendTopicToOne("/chat/single/",message);
    }
    //demo4
    //@MessageMapping("/v4/polling/chat")
    @Scheduled(fixedRate = 3000)
    public void pollingVM() {

        ws.pollingVMToAll("/topic/polling");
    }
    //demo5
    //@MessageMapping("/v5/polling/chat")
    @Scheduled(fixedRate = 3000)
    public void pollingStock() {

        ws.pollingStockToAll("/topic/stock");
    }
}
