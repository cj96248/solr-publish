package com.trainning.project.cd.json;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class ConvertJSON {
    public static List<CDBean> jsonToList(String json){
        List<CDBean> list = JSON.parseArray(json,CDBean.class);//把JSON文本parse成JavaBean集合 
        list.stream().forEach(System.out::println);//打印一下list的内容，调试时用
        return list;
    }
}
