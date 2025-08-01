package com.bash.jsouppractice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebScraper {
    public static void main(String[] args) {
        String url = "https://books.toscrape.com/";
        try{
            Document doc = Jsoup.connect(url).get();
            Elements books = doc.select(".product_pod");
            System.out.println("BOOK WEB SCRAPPER");
            System.out.println("---------------------------");
            for (Element book : books) {
                String title = book.select("h3 > a").text();
                String price = book.select(".price_color").text();
                System.out.println(title + " - " + price);
            }
            System.out.println("---------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
