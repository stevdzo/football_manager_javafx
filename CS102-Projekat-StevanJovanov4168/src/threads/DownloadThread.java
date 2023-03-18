/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.IOException;
import javafx.application.Platform;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author steva
 */
public class DownloadThread implements Runnable {

    private String strWeather;
    private String strDate;
    
    public DownloadThread() {
    }

    public DownloadThread(String strWeather) {

        this.strWeather = strWeather;
    }
    
    public DownloadThread(String strWeather, String strDate) {

        this.strWeather = strWeather;
        this.strDate = strDate;
    }

    public String getStrWeather() {
        return strWeather;
    }
    
    public void setStrWeather(String strWeather) {
        this.strWeather = strWeather;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }  

    @Override
    public void run() {

        try {

            Platform.runLater(() -> {

                try {
                    Document docWeather = Jsoup.connect("https://www.accuweather.com/en/gb/london/ec4a-2/weather-forecast/328328").get();
//                    Elements elW = docWeather.select(".content-module div.day-panel span.high");
//                    
//                    strWeather = "Current temperature: " + elW.get(0).html() + "C";
//                    
//                    Document docDate = Jsoup.connect("https://www.timeanddate.com/").get();
//                    Elements elD = docDate.select(".fixed div.rd-inner p span#ij2");
//                    
//                    strDate = elD.get(0).html();
                    
                    
                } catch (IOException ex) {
                    
                    ex.printStackTrace();
                }
            });

            Thread.sleep(100);

        } catch (InterruptedException ex) {

            ex.getMessage();
        }
    }

}
