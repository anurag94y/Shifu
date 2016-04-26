package com.example.flamealchemist.jsoup_check.model;

/**
 * Created by Flame Alchemist on 11/14/2015.
 */

public class Favourites {
    long id;
    String name;
    String price;
    String link;

    public Favourites(){

    }

    public Favourites(String name , String price, String link){
        this.name = name;
        this.price = price;
        this.link = link;
    }

    public void setId( long id ){
        this.id = id;
    }

    public void setName( String name){
        this.name = name;
    }

    public  void  setPrice( String price){
        this.price = price;
    }

    public  void setLink( String link){
        this.link = link;
    }

    public long getId( ){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public  String  getPrice(){
        return this.price;
    }

    public  String getLink(){
        return this.link;
    }
}
