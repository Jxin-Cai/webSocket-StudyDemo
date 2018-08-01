package com.cjx.websocket.demo.util;


import java.io.Reader;

import java.util.List;

/**
 * @author:蔡佳新
 * @date:创建时间 20:04 2018/8/1
 * @note:
 */
public interface GsonUtil {

/*    public static <T> Result<List<T>> fromJsonArray(Reader reader, Class<T> clazz) {
        Type type = TypeBuilder
                .newInstance(Result.class)
                .beginSubType(List.class)
                .addTypeParam(clazz)
                .endSubType()
                .build();
        return GSON.fromJson(reader, type);
    }

    public static <T> Result<T> fromJsonObject(Reader reader, Class<T> clazz) {
        Type type = TypeBuilder
                .newInstance(Result.class)
                .addTypeParam(clazz)
                .build();
        return GSON.fromJson(reader, type);
    }*/

}
