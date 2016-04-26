package com.example.flamealchemist.jsoup_check.Crawler_Package;

/**
 * Created by Flame Alchemist on 11/16/2015.
 */
import java.net.*;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

class Price_Check {
    private String query;
    private int Company;
    Price_Check( String in ) {
        query = in;
        Company = Find();
    }

    public int Find() {
        int idx = 0;
        int len = query.length();
        while(idx < len && query.charAt(idx) != '.') {
            idx++;
        }
        idx++;

        if(idx != len) {

            if(query.charAt(idx) == 'f')
                return 1;
            if(query.charAt(idx) == 'a')
                return 2;
            if(idx + 1 < len && query.charAt(idx) == 's' && query.charAt(idx + 1) == 'h')
                return 3;
            if(query.charAt(idx) == 'e')
                return 4;
            if(query.charAt(idx) == 's')
                return 5;

        } else { // Error
            return -1;
        }
        return 0;
    }

    public void Print(String str) {
        System.out.println("str => " + str);
    }

    public String GetPrice() {
        if(Company == 1) { // Flipkart

            Flipkart fp = new Flipkart();
            return fp.GetPrice();

        } else if(Company == 2) { // Amazon

            Amazon amz = new Amazon();
            return amz.GetPrice();

        } else if(Company == 3) { // ShopClues

            ShopClues sp = new ShopClues();
            return sp.GetPrice();

        } else if(Company == 4) { //Ebay

            Ebay eb = new Ebay();
            return eb.GetPrice();

        } else if(Company == 5) { // Snapdeal

            Snapdeal sd = new Snapdeal();
            return sd.GetPrice();

        }

        return "Error";
    }

    public class Flipkart {
        Element ProductInfo;
        Flipkart() {
            try{
                    System.out.println("--------------------------In Flipkart--------------------- ");


                String s= query;
                Document doc = null;

                int cnt = 1;
                while(cnt-- > 0) {
                        System.out.println("--------------------------"+cnt+"--------------------- ");
                    try {
                        doc = Jsoup.connect(s).ignoreHttpErrors(true).timeout(3000).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US;   rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").get();
                        break;
                    } catch(UnknownHostException e) {
                        try {
                            Thread.sleep(10000);
                        } catch(InterruptedException ie) {

                        }
                    } catch(SocketTimeoutException se) {

                    }
                }

                if(cnt == -1) {
                    System.out.println("--------------------------End--------------------- ");
                    return;
                }
                ProductInfo = doc.select("div.shop-section").first();

            } catch (IOException e){
                e.printStackTrace();
            }

        }

        public String GetPrice() {
            try {
                return ProductInfo.select("span.selling-price").first().text();
            } catch(NullPointerException np) {
                return "";
            }

        }

    }

    public class Amazon {
        Element ProductInfo;
        Amazon() {
            try {
                System.out.println("--------------------------In A--------------------- ");
                String s = query;
                Document doc = null;
                int cnt = 1;
                while(cnt-- > 0) {
                    try {
                        doc = Jsoup.connect(s).ignoreHttpErrors(true).timeout(3000).userAgent("Mozilla/5.0 (Macintosh; U; Intel Mac OS X; de-de) AppleWebKit/523.10.3 (KHTML, like Gecko) Version/3.0.4 Safari/523.10").get();
                        break;
                    } catch(UnknownHostException e) {
                        //                            logger.log(e.getMessage());
                        try {
                            Thread.sleep(10000);
                        } catch(InterruptedException ie) {

                        }
                    } catch(SocketTimeoutException se) {

                    }
                }

                if(cnt == -1) {
                    System.out.println("--------------------------End--------------------- ");
                    return;
                }
                ProductInfo = doc.select("#centerCol").first();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String GetPrice() {
            try {
                return ProductInfo.select("span.a-color-price").first().text();
            } catch (NullPointerException np) {
                return "";
            }
        }

    }

    public class ShopClues {

        Element ProductInfo;
        ShopClues() {
            try{
                System.out.println("--------------------------In SC--------------------- ");
                String s = query;
                Document doc = null;
                int cnt = 1;
                while(cnt-- > 0) {
                    try {
                        doc = Jsoup.connect(s).ignoreHttpErrors(true).timeout(3000).userAgent("Mozilla/5.0 (Macintosh; U; Intel Mac OS X; de-de) AppleWebKit/523.10.3 (KHTML, like Gecko) Version/3.0.4 Safari/523.10").get();
                        break;
                    } catch(UnknownHostException e) {
                        //                            logger.log(e.getMessage());
                        try {
                            Thread.sleep(10000);
                        } catch(InterruptedException ie) {

                        }
                    } catch(SocketTimeoutException se) {

                    }
                }
                if(cnt == -1) {
                    System.out.println("--------------------------End--------------------- ");
                    return;
                }
                ProductInfo = doc.select("div.product").first();

            } catch(IOException e){
                e.printStackTrace();
            }

        }

        public String GetPrice() {
            try {
                return ProductInfo.select("div.price").first().text();
            } catch (NullPointerException np) {
                return "";
            }
        }

    }

    public class Ebay {
        Element ProductInfo;
        Ebay() {
            try {
                System.out.println("--------------------------In E--------------------- ");
                String s =  query;

                Document doc = null;
                int cnt = 1;
                while(cnt-- > 0) {
                    try {
                        doc = Jsoup.connect(s).ignoreHttpErrors(true).timeout(3000).userAgent("Mozilla/5.0 (Macintosh; U; Intel Mac OS X; de-de) AppleWebKit/523.10.3 (KHTML, like Gecko) Version/3.0.4 Safari/523.10").get();
                        break;
                    } catch(UnknownHostException e) {
                        //                            logger.log(e.getMessage());
                        try {
                            Thread.sleep(10000);
                        } catch(InterruptedException ie) {

                        }
                    } catch(SocketTimeoutException se) {

                    }
                }
                if(cnt == -1) {
                    System.out.println("--------------------------End--------------------- ");
                    return;
                }
                ProductInfo = doc.select("#mainContent").first();

            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        public String GetPrice() {

            try {
                return ProductInfo.select("#prcIsum").first().text();
            } catch (NullPointerException np) {
                return "";
            }
        }
    }

    public class Snapdeal {
        Element ProductInfo;
        Snapdeal() {
            try{
                System.out.println("--------------------------In SD--------------------- ");
                String s = query;
                Document doc = null;
                int cnt = 1;
                while(cnt-- > 0) {
                    try {
                        doc = Jsoup.connect(s).ignoreHttpErrors(true).timeout(3000).userAgent("Mozilla/5.0 (Macintosh; U; Intel Mac OS X; de-de) AppleWebKit/523.10.3 (KHTML, like Gecko) Version/3.0.4 Safari/523.10").get();
                        break;
                    } catch(UnknownHostException e) {
                        //                            logger.log(e.getMessage());
                        try {
                            Thread.sleep(10000);
                        } catch(InterruptedException ie) {

                        }
                    } catch(SocketTimeoutException se) {

                    }
                }
                if(cnt == -1) {
                    System.out.println("--------------------------End--------------------- ");
                    return;
                }
                ProductInfo = doc.select("#buyPriceBox").first();


            } catch (IOException e){
                e.printStackTrace();
            }

        }

        public String GetPrice() {
            try {
                return ProductInfo.select("span.payBlkBig").first().text();
            } catch (NullPointerException np) {
                return "";
            }
        }
    }

}

public class New_Price {

    public String GetPrice(String link) {
        System.out.println("---------------------------- In new price -----------------------------");
            Price_Check pr_ch = new Price_Check(link);

            String p = pr_ch.GetPrice();
            System.out.println("---------------------------- "+ p+" -----------------------------");
            return p;
    }
}
