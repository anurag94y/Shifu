package com.example.flamealchemist.jsoup_check.Tools;

import java.io.Serializable;
import java.util.Vector;

/**
 * Created by Flame Alchemist on 11/13/2015.
 */
public class GetSet implements Serializable {

    Vector<String> Names, Links ,Prices;

    public void set(Vector<String> N, Vector<String> P, Vector<String> L){
        Names = new Vector<String>();
        Prices = new Vector<String>();
        Links = new Vector<String>();
        Names = N;
        Prices = P;
        Links = L;
        return;
    }

    public Vector<String> GetNames(){
        return Names;
    }

    public Vector<String> GetLinks(){
        return Links;
    }

    public Vector<String> GetPrices(){
        return Prices;
    }
}
