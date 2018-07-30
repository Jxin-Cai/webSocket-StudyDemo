package com.cjx.websocket.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author:蔡佳新
 * @date:创建时间 18:10 2018/7/30
 * @note:
 */
@Data
@Builder()
@NoArgsConstructor
@AllArgsConstructor
public class OutMessage {
    private String origin;

    private String content;

    private LocalDateTime time;
}
