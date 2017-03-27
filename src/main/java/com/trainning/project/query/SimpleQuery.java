package com.trainning.project.query;

import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.apache.solr.parser.QueryParser;
import org.apache.solr.search.QParser;
import org.apache.solr.search.SyntaxError;

/**
* @author JiangChao
* @date 2017年3月27日下午10:53:09
*/
public class SimpleQuery {
    public void parse(QParser analyzer) throws SyntaxError{
        QueryParser parser = new QueryParser(Version.LUCENE_6_0_0,"body", analyzer);
        Query bodyQuery = parser.parse("IBM");
        
    }
}
