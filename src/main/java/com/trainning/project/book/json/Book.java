package com.trainning.project.book.json;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class Book {
    
    @JSONField(name="mediaList")
    private List<BookInfo> book;

    public List<BookInfo> getBook() {
        return book;
    }

    public void setBook(List<BookInfo> book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Book [" + book + "]";
    }
    
}
