package com.trainning.project.custom;

import org.apache.lucene.analysis.util.CharTokenizer;
import org.apache.lucene.util.AttributeFactory;

/**
* @author JiangChao
* @date 2017年4月2日下午9:46:18
*/
public class MyVerticalLineTokenizer extends CharTokenizer {

    public MyVerticalLineTokenizer() {
        
    }
    public MyVerticalLineTokenizer(AttributeFactory factory) {
        super(factory);
      }
      
      /** Collects only characters which do not satisfy
       *  参数 c 指的是term的ASCII值，竖线的值为 124
       */
      @Override
      protected boolean isTokenChar(int c) {
        return !(c == 124);
      }

}
