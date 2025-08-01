package com.bash.jsouppractice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Scraper {
    private static final int MAX_RETRIES = 3;
    public static void main(String[] args) {
        String url =  "https://en.wikipedia.org/wiki/Web_scraping";
        Document document = scrape(url);
        if(document != null){
            extractContent(document);
        } else System.out.println("Document is null");
    }

    private static void extractContent(Document document) {
        System.out.println("title: " + document.title());
        Elements headings = document.select("h1, h2, h3, h4, h5, h6");
        for(Element heading : headings){
            System.out.println("heading: " + heading.text());
        }
        Elements links = document.select("a[href]");
        for(Element link : links){
            System.out.println("link: " + link.attr("abs:href") + " - Text: " + link.text());
        }
        Elements images = document.select("img[src]");
        for(Element image : images){
            String imageUrl = image.absUrl("src");
            System.out.println("Full Image Url: " + imageUrl);
        }
        Elements paragraphs = document.select("p");
        for(Element paragraph : paragraphs){
            System.out.println("Paragraph: " + paragraph.text());
        }
//        Extratx JSon-LD data
        Elements jsonElements = document.select("script[type=application/ld+json]");
        for(Element jsonElement : jsonElements){
            System.out.println("JSON-LD data: " + jsonElement.html());
        }
    }

    private static Document scrape(String url) {
        int attempt = 0;
        while (attempt < MAX_RETRIES) {
            try{
                Document doc = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36")
                        .timeout(10000)
                        .get();
                System.out.println("Successfully scraped " + url);
                return doc;
            } catch (IOException e){
                attempt++;
                System.err.println("Attempt " + attempt + " failed for url " + url + ": " + e.getMessage());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        }
        return null;
    }
}
