package com.cjx.websocket.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * @author:蔡佳新
 * @date:创建时间 9:18 2018/7/31
 * @note: 连接事件
 *
 * 		websocket模块监听器类型：
 * 			SessionSubscribeEvent 	订阅事件
 * 			SessionUnsubscribeEvent	取消订阅事件
 * 			SessionDisconnectEvent 	断开连接事件
 * 			SessionDisconnectEvent 	建立连接事件
 */
@Component
@Slf4j
public class ConnectEventListener implements ApplicationListener<SessionConnectEvent> {
    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor headerAccessor =  StompHeaderAccessor.wrap(event.getMessage());
        log.info("【ConnectEventListener监听器事件 类型】{}",headerAccessor.getCommand().getMessageType());
    }
}
