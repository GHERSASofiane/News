package com.ghersa.news;

import java.util.ArrayList;

public class Object {

    private String status ;
    private int totalResults;
    private ArrayList<Article> articles;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getTotalResults() {
        return totalResults;
    }
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }
    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public void object(){
        this.status = "";
        this.totalResults = 0;
        this.articles = new ArrayList<>();
    }

}
