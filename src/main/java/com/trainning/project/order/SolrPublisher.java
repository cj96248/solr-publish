package com.trainning.project.order;

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
        String filepath = "src/main/java/com/trainning/project/order/order.xml";
        List<OrderInfo> list = ReadXMLFile.getXMLFileList(filepath);
//        client.deleteByQuery("*:*"); //清空所以数据
        list.stream().forEach(System.out::println);
        for(OrderInfo order : list){
            SolrInputDocument input = new SolrInputDocument();
            //ID是数据的主键，原则上必须指定，并且不能重复。便于数据管理，如删除操作
            input.addField("id", order.getId());
            //这里的addField方法第一个参数需要在core0/conf/managed-schema中有对应的field
            //参数使用的是managed-schema中有对应的field：<dynamicField name="*_s" type="string" indexed="true" stored="true"/>
            //这个参数采用通配符的方式，指定一个string类型的字段
            input.addField("recipients_s", order.getRecipients());
            input.addField("address_s", order.getAddress());
            //日期字段*_dt,需要插入的数据格式，日期T时间Z 如：2013-12-26T21:47:04Z
            input.addField("odertime_dt", order.getOrdertime());
            input.addField("status_s", order.getStatus());
            input.addField("total_d", order.getTotal());
            input.addField("phone_s", order.getPhone());
            input.addField("type_s", "订单");
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
