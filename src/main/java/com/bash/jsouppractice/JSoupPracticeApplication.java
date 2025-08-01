package com.bash.jsouppractice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class JSoupPracticeApplication {

    public static void main(String[] args) {

        String url = "https://books.toscrape.com/";
        try{
            Document doc = Jsoup.connect(url).get();
            books = doc.select("")
        } catch (IOException e) {
            e.printStackTrace();
        }



        SpringApplication.run(JSoupPracticeApplication.class, args);
    }

}
