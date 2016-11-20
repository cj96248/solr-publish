package com.trainning.project.ebook;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXMLFile {
    public static List<EBook> getXMLFileList(String filepath) {
        //创建文件对象
        File file = new File(filepath);
        //构建SAX读写器
        SAXReader saxReader = new SAXReader();
        //将XML文件内容保存在List中
        List<EBook> ebooks = new ArrayList<EBook>();
        try {
            //加载文件
            Document document = saxReader.read(file);
            //获取根元素
            Element root =document.getRootElement();
            //获取根元素下的所有子元素
            List<Element> list = root.elements();
            //将文件元素内容赋值给java bean
            for(Element e : list){
                EBook book = new EBook();
                book.setId(e.attributeValue("id"));
                book.setCategory(e.attributeValue("category"));
                book.setTitle(e.elementText("title"));
                book.setAuthor(e.elementText("author"));
                book.setPubDate(e.elementText("pubDate"));
                book.setPublisher(e.elementText("publisher"));
                book.setSize(e.elementText("size"));
                book.setPrice(Double.parseDouble(e.elementText("price")));
                System.out.println(book);
                ebooks.add(book);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return ebooks;
    }
    
    public static void main(String[] args) {
        String filepath = "src/main/java/com/trainning/project/ebook/ebook.xml";
        getXMLFileList(filepath);
    }
}
