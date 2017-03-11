package com.trainning.project.book.json;

import com.alibaba.fastjson.annotation.JSONField;

public class BookInfo {
    
    @JSONField(name="mediaId")
    private String id;
    private String title;
    @JSONField(name="categorys")
    private String category;
    @JSONField(name="authorPenname")
    private String author;
    private String pubDate;
    @JSONField(name="paperBookPrice")
    private int pages;
    @JSONField(name="originalPrice")
    private double price;

    private String publisher;
    @JSONField(name="descs")
    private String desc;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPubDate() {
        return pubDate;
    }
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
    public int getPages() {
        return pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    public String getDesc() {
        if(desc != null && desc.length() > 50){
            return desc.substring(0, 50);
        }
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    @Override
    public String toString() {
        return "BookInfo [id=" + id + ", title=" + title + ", category=" + category + ", author=" + author
                + ", pubDate=" + pubDate + ", pages=" + pages + ", price=" + price + ", publisher=" + publisher
                + ", desc=" + desc + "]";
    }
    
}
