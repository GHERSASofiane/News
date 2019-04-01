package com.ghersa.news;

public class Article {

    private int idNews = 0 ;
    private String author = "";
    private String title = "";
    private String description = "";
    private String url = "";
    private String urlToImage = "";
    private String publishedAt = "";
    private String content = "";

    public Article(int id, String a, String t, String u, String uimg, String p, String c){
        setidNews(id); setAuthor(a); setTitle(t);
        setUrl(u); setUrlToImage(uimg); setPublishedAt(p); setContent(c);
    }
    public Article(){    }

    public int getidNews() {
        return idNews;
    }
    public void setidNews(int arg) {
        this.idNews = arg;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrlToImage() {
        return urlToImage;
    }
    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
    public String getPublishedAt() {
        return publishedAt;
    }
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
