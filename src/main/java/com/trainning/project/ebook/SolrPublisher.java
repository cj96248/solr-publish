package com.trainning.project.ebook;

import java.util.List;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

public class SolrPublisher {
    public static final String SOLR_URL = "http://localhost:8080/solr/core";
    private HttpSolrClient client;
    public SolrPublisher() {
        client = new HttpSolrClient(SOLR_URL);
    }
    
    public void insertDataToSolr() throws Exception{
        String filepath = "src/main/java/com/trainning/project/ebook/ebook.xml";
        List<EBook> list = ReadXMLFile.getXMLFileList(filepath);
//        client.deleteByQuery("*:*"); //清空所以数据
        list.stream().forEach(System.out::println);
        for(EBook book : list){
            SolrInputDocument input = new SolrInputDocument();
            //ID是数据的主键，原则上必须指定，并且不能重复。便于数据管理，如删除操作
            input.addField("id", book.getId());
            //这里的addField方法第一个参数需要在core0/conf/managed-schema中有对应的field
            //参数使用的是managed-schema中有对应的field：<dynamicField name="*_s" type="string" indexed="true" stored="true"/>
            //这个参数采用通配符的方式，指定一个string类型的字段
            input.addField("catagory_s", book.getCategory());
            input.addField("title_s", book.getTitle());
            input.addField("author_s", book.getAuthor());
            input.addField("pubData_s", book.getPubDate());
            input.addField("price_d", book.getPrice());
            input.addField("size_s", book.getSize());
            input.addField("publisher_s", book.getPublisher());
            input.addField("type_s", "电子书");
            client.add(input);
        }
        //不能忘记提交
        client.commit();
    }
    
    public static void main(String[] args) {
        SolrPublisher publisher = new SolrPublisher();
        try {
            publisher.insertDataToSolr();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
