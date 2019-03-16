package com.ghersa.news;

import android.util.Log;
import com.owlike.genson.Genson;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ConnectAPI {

    public object GetArticleApi(){
        object result = new object();
        HttpURLConnection urlConnect = null;
        try {
            URL url = new URL("https://newsapi.org/v2/top-headlines?country=fr&apiKey=fb82207d6c214614bc18937bb5e0f4f3&pageSize=100");
            urlConnect = (HttpURLConnection) url.openConnection();
            urlConnect.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(urlConnect.getInputStream());
            Scanner scanner = new Scanner(in);
            result = new Genson().deserialize(scanner.nextLine(), object.class);
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