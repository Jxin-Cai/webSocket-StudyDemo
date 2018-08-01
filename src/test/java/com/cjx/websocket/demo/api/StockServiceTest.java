package com.cjx.websocket.demo.api;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author:蔡佳新
 * @date:创建时间 9:45 2018/8/1
 * @note:
 */
public class StockServiceTest {

    @Test
    public void getStockInfo() {
        List<Map<String, Object>> list =StockService.getStockInfo();
        String msgTpl = "名称: %s ; 价格: %s元 ; 最高价: %s ; 最低价: %s ; 涨跌幅: %s ; 昨日收盘价: %s ; 总市值: %s"+System.getProperty("line.separator");
        StringBuffer msg = new StringBuffer();

        list.stream().forEach(map->msg.append(
                String.format(msgTpl, map.get("name"), map.get("nowPrice"), map.get("maxPrice"),
                        map.get("minPrice"), map.get("diff_rate"), map.get("yestodayClosePrice"), map.get("tradeAmount") )

        ));
        System.out.println(msg.toString());
    }
}