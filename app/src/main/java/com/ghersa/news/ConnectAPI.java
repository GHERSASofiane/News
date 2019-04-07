package com.ghersa.news;

import android.util.Log;
import com.owlike.genson.Genson;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ConnectAPI {

    //  Pour les catégorie
    public Object GetTopHeadlines(String pays){
        return AppelerAPI("https://newsapi.org/v2/top-headlines?country="+pays+"&apiKey=fb82207d6c214614bc18937bb5e0f4f3&pageSize=100");
    }
    //  Pour les catégorie
    public Object Getcategory(String pays, String category){
        return AppelerAPI("https://newsapi.org/v2/top-headlines?country="+pays+"&category="+category+"&apiKey=fb82207d6c214614bc18937bb5e0f4f3&pageSize=100");
    }

    private Object AppelerAPI(String requet){
        Object result = new Object();
        HttpURLConnection urlConnect = null;
        try {
            URL url = new URL(requet);
            urlConnect = (HttpURLConnection) url.openConnection();
            urlConnect.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(urlConnect.getInputStream());
            Scanner scanner = new Scanner(in);
            result = new Genson().deserialize(scanner.nextLine(), Object.class);
            in.close();

            return result;

        }catch (Exception e){
            Log.i("Exception : ","Cannot fond HTTP "+e);
            return result;
        }finally {
            if(urlConnect != null) urlConnect.disconnect();
            return result;
        }
    }

//  Récuperer les news par rapport a un mot de recherche
    public Object GetKeywordsApi(String lg, String Keywords){
        Object result = new Object();
        HttpURLConnection urlConnect = null;
        try {
            URL url = new URL("https://newsapi.org/v2/everything?language="+lg+"&q="+Keywords+"&apiKey=fb82207d6c214614bc18937bb5e0f4f3&pageSize=100");
            urlConnect = (HttpURLConnection) url.openConnection();
            urlConnect.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(urlConnect.getInputStream());
            Scanner scanner = new Scanner(in);
            result = new Genson().deserialize(scanner.nextLine(), Object.class);
            in.close();

            return result;

        }catch (Exception e){
            Log.i("Exception : ","Cannot fond HTTP "+e);
            return result;
        }finally {
            if(urlConnect != null) urlConnect.disconnect();
            return result;
        }
    }

}
