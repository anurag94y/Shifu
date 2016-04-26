package com.example.flamealchemist.jsoup_check.Tools;

/**
 * Created by Flame Alchemist on 11/16/2015.
 */
import java.io.*;
import java.util.*;
import java.*;

public class Compare_Price {

    /* This Function return 1 if s1 > s2 otherwise 0 */
    public static int isBigger(String s1, String s2) {
        s1.trim();
        s2.trim();

        String nS1 = "";
        String nS2 = "";

        int idx1 = 0;
        int idx2 = 0;

        int len1 = s1.length();
        int len2 = s2.length();

        while(idx1 < len1 && !Character.isDigit(s1.charAt(idx1))) {
            idx1++;
        }

        while(idx2 < len2 && !Character.isDigit(s2.charAt(idx2))) {
            idx2++;
        }

        while(idx1 < len1) {
            if(s1.charAt(idx1) == ',') {
                idx1++;
                continue;
            }
            nS1 += s1.charAt(idx1);
            idx1++;
        }

        while(idx2 < len2) {
            if(s2.charAt(idx2) == ',') {
                idx2++;
                continue;
            }
            nS2 += s2.charAt(idx2);
            idx2++;
        }
        if(nS1.isEmpty()){
            nS1 = "10000000000";
        }

        if(nS2.isEmpty()){
            nS2 = "10000000000";
        }
        System.out.println(nS1 + " " + nS2);
        double d1 = Double.parseDouble(nS1);
        double d2 = Double.parseDouble(nS2);

        int val = Double.compare(d1, d2);
        if(val > 0)
            return 1;
        return 0;
    }
}
