package com.trainning.project.cd.json;

public class CDBean {
    private String title;
    private String artist;
    private String country;
    private String company;
    private double price;
    private String year;
    private String description;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "CD [title=" + title + ", artist=" + artist + ", country=" + country + ", company=" + company
                + ", price=" + price + ", year=" + year + ", description=" + description + "]";
    }
}
