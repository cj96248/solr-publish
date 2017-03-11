package com.trainning.project.book.json;

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
        String filepath = "src/main/java/com/trainning/project/book/json/book.json";
        String json = ReadJSONFile.readFile(filepath);
        List<Book> list = ConvertJSON.jsonToList(json);
//        client.deleteByQuery("*:*");//清空solr中原有数据
        for(Book book : list){
            SolrInputDocument input = new SolrInputDocument();
            //ID是数据的主键，原则上必须指定，并且不能重复。便于数据管理，如删除操作
            input.addField("id", book.getBook().get(0).getId());
            //这里的addField方法第一个参数需要在core0/conf/managed-schema中有对应的field
            //参数使用的是managed-schema中有对应的field：<dynamicField name="*_s" type="string" indexed="true" stored="true"/>
            //这个参数采用通配符的方式，指定一个string类型的字段
            input.addField("title_s", book.getBook().get(0).getTitle());
            input.addField("author_s", book.getBook().get(0).getAuthor());
            input.addField("category_s", book.getBook().get(0).getCategory());
            input.addField("page_i", book.getBook().get(0).getPages());
            input.addField("price_d", book.getBook().get(0).getPrice());
            //日期字段*_dt,需要插入的数据格式，日期T时间Z 如：2013-12-26T21:47:04Z
            input.addField("odertime_dt", "2015-08-02T21:47:04Z");
            input.addField("publisher_s", "不知名出版社");
            input.addField("type_s", "图书");
            input.addField("description_s", book.getBook().get(0).getDesc());
            client.add(input);
        }
        //不能忘记提交
        client.commit();
    }
    /**
     * 使用main方法测试整个插入过程是否能成功
     */
    public static void main(String[] args) {
        SolrPublisher publisher = new SolrPublisher();
        try {
            publisher.insertDataToSolr();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
