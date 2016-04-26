package com.example.flamealchemist.jsoup_check.model;

/**
 * Created by Flame Alchemist on 11/14/2015.
 */
public class History {
    long id;
    String query;
    String created_at;
    public History(){
    }

    public History(String query){
        this.query = query;
    }

    public void setId(long id){
        this.id = id;
    }

    public  void setQuery(String query){
        this.query = query;
    }

    public  void  setCreated_at(String created_at){
        this.created_at = created_at;
    }
    public long getId(){
        return this.id;
    }

    public String getQuery(){
        return this.query;
    }

    public String getCreated_at(){
        return this.created_at;
    }
}
