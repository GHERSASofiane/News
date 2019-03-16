package com.ghersa.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.owlike.genson.Genson;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    final List<article> items = new ArrayList<>();
    public object articles = new object();
    final ConnectAPI connectApi = new ConnectAPI();
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView lv = findViewById(R.id.listView);

         new Thread(new Runnable() {
            @Override
            public void run() {
                articles = connectApi.GetArticleApi();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        items.addAll(articles.getArticles());

                        final ArrayAdapter<article> adapter = new

                                AdapterItems(
                                MainActivity.this,
                                R.layout.item,
                                items);
                        lv.setAdapter(adapter);
//                        for (int i=0; i<articles.getArticles().size(); i++){
//
//                            Log.i("Exception : "," : "+ articles.getArticles().get(i).toString()  );
//                        }
                    }
                });
            }
        }).start();






            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i("GHERSA","SALUTTTTT :: "+ position);
                }
            });


    }

}
