package com.cjx.websocket.demo.api;

import com.cjx.websocket.demo.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author:蔡佳新
 * @date:创建时间 14:27 2018/7/31
 * @note: 股票行情接口服务
 */
/**
 * 重要提示如下:
 * HttpUtils请从
 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
 * 下载
 *gson 类属性别名注解@SerializedName(value = "emailAddress", alternate = {"email", "email_address"})
 * new GsonBuilder()更改gson配置
 * @Expose //
 * @Expose(deserialize = true,serialize = true) //序列化和反序列化都都生效，等价于上一条
 * @Expose(deserialize = true,serialize = false) //反序列化时生效
 * @Expose(deserialize = false,serialize = true) //序列化时生效
 * @Expose(deserialize = false,serialize = false) // 和不写注解一样
 *

 */
@Slf4j
public class StockService {
    public static List<Map<String,Object>> getStockInfo(){
        String host = "https://ali-stock.showapi.com";
        String path = "/stockIndex";
        String method = "GET";
        String appcode = "227bb3669a3d484d876e366a29c6d0cc";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("stocks", "sh000001,sz399001,sz399005,sz399006,hkhsi");
        List<Map<String,Object>> list = null;

        try {

            HttpResponse response = HttpUtil.doGet(host, path, method, headers, querys);
            log.info(response.toString());
            String result = EntityUtils.toString(response.getEntity());//获得响应实体数据
            Gson gson = new Gson();
            //gson.fromJson(carryPersons, new TypeToken<List<CarryPerson>>() {}.getType());
            Map<String,Object> map = gson.fromJson(result, new TypeToken<Map<String, Object>>() {}.getType());
            list = (List<Map<String,Object>>) Optional.ofNullable(map.get("showapi_res_body")).map(item->
                ((Map) item).get("indexList")
            ).get();
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;

    }
}
