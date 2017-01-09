package ru.whoy.url.get;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by SBT-Sergunin-SV on 09.01.2017.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.ya.ru");
        URLConnection connection = url.openConnection();
//        try(InputStream is = url.openStream()) {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//            String s;
//            while ((s = reader.readLine()) != null) {
//                System.out.println(s);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
