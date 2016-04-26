package com.example.flamealchemist.jsoup_check.model;

/**
 * Created by Flame Alchemist on 11/14/2015.
 */
public class Notifications {
    long id;
    String body;
    String created_at;
    boolean viewed;

    public Notifications(){

    }

    public Notifications(String body, String created_at, boolean viewed){
        this.body = body;
        this.created_at = created_at;
        this.viewed = viewed;
    }

    public void setId( long id ){
        this.id = id;
    }

    public void setBody( String body){
        this.body = body;
    }

    public  void  setCreated_at( String created_at){
        this.created_at = created_at;
    }

    public  void setViewed( boolean viewed){
        this.viewed = viewed;
    }

    public long getId( ){
        return this.id;
    }

    public String getBody(){
        return this.body;
    }

    public  String  getCreated_at(){
        return this.created_at;
    }

    public  boolean setViewed(){
        return this.viewed;
    }

}
