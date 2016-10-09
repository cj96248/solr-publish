package com.trainning.project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXMLFile {
    public static List<CD> getXMLFileList(String filepath) {
        //创建文件对象
        File file = new File(filepath);
        //构建SAX读写器
        SAXReader saxReader = new SAXReader();
        //将XML文件内容保存在List中
        List<CD> cds = new ArrayList<CD>();
        try {
            //加载文件
            Document document = saxReader.read(file);
            //获取根元素
            Element root =document.getRootElement();
            //获取根元素下的所有子元素
            List<Element> list = root.elements();
            //将文件元素内容赋值给java bean
            for(Element e : list){
                CD cd = new CD();
                cd.setTitle(e.elementText("TITLE"));
                cd.setArtist(e.elementText("ARTIST"));
                cd.setCompany(e.elementText("COMPANY"));
                cd.setCountry(e.elementText("COUNTRY"));
                cd.setPrice(Double.parseDouble(e.elementText("PRICE")));
                cd.setYear(e.elementText("YEAR"));
                cd.setDescription(e.elementText("DESCRIPTION"));
                System.out.println(cd);
                cds.add(cd);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return cds;
    }
    
    public static void main(String[] args) {
        String filepath = "src/main/java/com/trainning/project/cd.xml";
        getXMLFileList(filepath);
    }
}
