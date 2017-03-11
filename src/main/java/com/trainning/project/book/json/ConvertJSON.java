package com.trainning.project.book.json;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ConvertJSON {
    public static List<Book> jsonToList(String text){
        JSONObject obj = JSONObject.parseObject(text);
        JSONObject data = obj.getJSONObject("data");
        JSONArray sale = data.getJSONArray("saleList");
        String json = JSONArray.toJSONString(sale);
        System.out.println("JSON : " + json);
        List<Book> list = JSON.parseArray(json,Book.class);//把JSON文本parse成JavaBean集合 
        list.stream().forEach(System.out::println);//打印一下list的内容，调试时用
        return list;
    }
    public static void main(String[] args) {
        String readFile = ReadJSONFile.readFile("src/main/java/com/trainning/project/book/json/book.json");
        List<Book> list = jsonToList(readFile);
        System.out.println(list.get(0));
    }
}
