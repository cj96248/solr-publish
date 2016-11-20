package com.trainning.project.book;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXMLFile {
    public static List<Book> getXMLFileList(String filepath) {
        //创建文件对象
        File file = new File(filepath);
        //构建SAX读写器
        SAXReader saxReader = new SAXReader();
        //将XML文件内容保存在List中
        List<Book> cds = new ArrayList<Book>();
        try {
            //加载文件
            Document document = saxReader.read(file);
            //获取根元素
            Element root =document.getRootElement();
            //获取根元素下的所有子元素
            List<Element> list = root.elements();
            //将文件元素内容赋值给java bean
            for(Element e : list){
                Book book = new Book();
                book.setId(e.attributeValue("id"));
                book.setCategory(e.attributeValue("category"));
                book.setTitle(e.elementText("title"));
                book.setAuthor(e.elementText("author"));
                book.setPubDate(e.elementText("pubDate"));
                book.setPublisher(e.elementText("publisher"));
                book.setPages(Integer.parseInt(e.elementText("pages")));
                book.setPrice(Double.parseDouble(e.elementText("price")));
                System.out.println(book);
                cds.add(book);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return cds;
    }
    
    public static void main(String[] args) {
        String filepath = "src/main/java/com/trainning/project/book/book.xml";
        getXMLFileList(filepath);
    }
}
