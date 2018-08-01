package com.cjx.websocket.demo.config.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @author:蔡佳新
 * @date:创建时间 17:45 2018/7/30
 * @note:
 */
@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class websocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * @Note: 注册端点，发布或者订阅消息的时候需要连接此端点,setAllowedOrigins 非必须，*表示允许其他域进行连接,withSockJS  表示开始sockejs支持
     * @Paran: registry 终端点注册器
     * @Return:
     * @Author:蔡佳新
     * @date:创建时间 17:49 2018/7/30
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/endpoint").setAllowedOrigins("*").withSockJS();
    }
    /**
     * @Note: 配置消息代理(中介) ， enableSimpleBroker 服务端推送给客户端的路径前缀 ，setApplicationDestinationPrefixes  客户端发送数据给服务器端的一个前缀
     * @Paran:
     * @Return:
     * @Author:蔡佳新
     * @date:创建时间 17:54 2018/7/30
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/chat");
        registry.setApplicationDestinationPrefixes("/app");//前缀
    }
}
