package com.cjx.websocket.demo.util;

import java.awt.*;
import java.io.File;
import java.util.Arrays;

/**
 * @author:蔡佳新
 * @date:创建时间 17:44 2018/8/1
 * @note:
 */
public class CommontUtil {

    public static void queryName(){
        File file = new File("C:\\Users\\Single\\Desktop\\华睿丰盛\\华睿丰盛整体资料\\表单模板\\宝供\\行政类流程表单模板");

        String[] list = file.list();

        //Arrays.stream(list).map(str-> ).toArray();
        for(String str:list){
            System.out.println(str.substring(!(str.indexOf("：")<0)?str.indexOf("：")+1:0,!(str.lastIndexOf(".")<0)?str.lastIndexOf("."):str.length()).trim());
        }
    }
}
