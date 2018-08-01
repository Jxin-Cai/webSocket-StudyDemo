package com.cjx.websocket.demo.ws;

import com.cjx.websocket.demo.model.InMessage;
import com.cjx.websocket.demo.model.OutMessage;
import com.cjx.websocket.demo.api.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:蔡佳新
 * @date:创建时间 8:46 2018/7/31
 * @note:
 */
@Component
public class WebsocketService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    public void sendTopicToAll(String point, InMessage message) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            Thread.sleep(100L);
            simpMessagingTemplate.convertAndSend(point,
                    OutMessage.builder()
                            .content(message.getContent() + i)
                            .time(LocalDateTime.now()).build());
        }
    }

    public void sendTopicToOne(String point,InMessage message) {
        simpMessagingTemplate.convertAndSend(point+message.getTarget(),
                OutMessage.builder()
                        .content(message.getOrigin()+" 发送:"+ message.getContent())
                        .time(LocalDateTime.now()).build());
    }

    public void pollingVMToAll(String point) {

        int processors = Runtime.getRuntime().availableProcessors();

        Long freeMem = Runtime.getRuntime().freeMemory();

        Long maxMem = Runtime.getRuntime().maxMemory();

        String message = String.format("服务器可用处理器:%s; 虚拟机空闲内容大小: %s; 最大内存大小: %s", processors,freeMem,maxMem );

        simpMessagingTemplate.convertAndSend(point,OutMessage.builder()
                .content(message)
                .time(LocalDateTime.now())
                .build());

    }

    public void  pollingStockToAll(String point) {

        List<Map<String, Object>> list =StockService.getStockInfo();
        String msgTpl = "名称: %s ; 价格: %s元 ; 最高价: %s ; 最低价: %s ; 涨跌幅: %s ; 昨日收盘价: %s ; 总市值: %s"+System.getProperty("line.separator");
        StringBuffer msg = new StringBuffer();

        list.stream().forEach(map->msg.append(
                String.format(msgTpl, map.get("name"), map.get("nowPrice"), map.get("maxPrice"),
                        map.get("minPrice"), map.get("diff_rate"), map.get("yestodayClosePrice"), map.get("tradeAmount") )

        ));

            simpMessagingTemplate.convertAndSend(point,OutMessage.builder()
                                    .content(msg.toString())
                                    .time(LocalDateTime.now())
                                    .build());
    }

}
