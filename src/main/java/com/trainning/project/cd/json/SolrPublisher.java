package com.trainning.project.cd.json;

import java.util.List;
import java.util.UUID;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

public class SolrPublisher {
    public static final String SOLR_URL = "http://localhost:8080/solr/core0";
    private HttpSolrClient client;
    public SolrPublisher() {
        client = new HttpSolrClient(SOLR_URL);
    }
    
    public void insertDataToSolr() throws Exception{
        String filepath = "src/main/java/com/trainning/project/json/cd.json";
        String json = ReadJSONFile.readFile(filepath);
        List<CDBean> list = ConvertJSON.jsonToList(json);
        client.deleteByQuery("*:*");//清空solr中原有数据
        for(CDBean cd : list){
            SolrInputDocument input = new SolrInputDocument();
            //ID是数据的主键，原则上必须指定，并且不能重复。便于数据管理，如删除操作
            input.addField("id", UUID.randomUUID().toString(), 1.0f);
            //这里的addField方法第一个参数需要在core0/conf/managed-schema中有对应的field
            //参数使用的是managed-schema中有对应的field：<dynamicField name="*_s" type="string" indexed="true" stored="true"/>
            //这个参数采用通配符的方式，指定一个string类型的字段
            input.addField("title_s", cd.getTitle());
            input.addField("artist_s", cd.getArtist());
            input.addField("country_s", cd.getCountry());
            input.addField("company_s", cd.getCompany());
            input.addField("price_d", cd.getPrice());
            input.addField("year_s", cd.getYear());
            input.addField("type_s", "光盘");
            input.addField("description_s", cd.getDescription());
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
