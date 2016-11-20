package com.trainning.project.order;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXMLFile {
    public static List<OrderInfo> getXMLFileList(String filepath) {
        //创建文件对象
        File file = new File(filepath);
        //构建SAX读写器
        SAXReader saxReader = new SAXReader();
        //将XML文件内容保存在List中
        List<OrderInfo> orders = new ArrayList<OrderInfo>();
        try {
            //加载文件
            Document document = saxReader.read(file);
            //获取根元素
            Element root =document.getRootElement();
            //获取根元素下的所有子元素
            List<Element> list = root.elements();
            //将文件元素内容赋值给java bean
            for(Element e : list){
                OrderInfo order = new OrderInfo();
                order.setId(e.attributeValue("id"));
                order.setOrdertime(e.elementText("ordertime"));
                order.setRecipients(e.elementText("recipients"));
                order.setStatus(e.elementText("status"));
                order.setPhone(e.elementText("phone"));
                order.setAddress(e.elementText("address"));
                order.setTotal(Double.parseDouble(e.elementText("total")));
                System.out.println(order);
                orders.add(order);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    public static void main(String[] args) {
        String filepath = "src/main/java/com/trainning/project/order/order.xml";
        getXMLFileList(filepath);
    }
}
