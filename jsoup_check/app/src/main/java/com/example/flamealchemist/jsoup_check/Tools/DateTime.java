package com.example.flamealchemist.jsoup_check.Tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Flame Alchemist on 11/14/2015.
 */
public class DateTime {
    public String getDateTime(){
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String format = s.format(new Date());
        return format;
    }
}
