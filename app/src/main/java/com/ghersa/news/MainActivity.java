package com.ghersa.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final List<article> items = new ArrayList<>();
    private object articles = new object();
    final ConnectAPI connectApi = new ConnectAPI();
    private ListView listView;
    private TextView Research;
    private Button button_Research;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        Research = findViewById(R.id.Research);
        button_Research = findViewById(R.id.button_Research);


        init("country","fr");// Init la recherche
//      a chaque clique dans la barre de recherche on met a jour notre fil d'actuelité
        button_Research.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = Research.getText().toString();
                Research(tmp);
            }
        });

    }

    public void Research(final String key){
        new Thread(new Runnable() {
            @Override
            public void run() {
                articles = connectApi.GetKeywordsApi(key);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        items.clear();
                        items.addAll(articles.getArticles());
                        ArrayAdapter<article> adapter = new
                                AdapterItems(
                                MainActivity.this,
                                R.layout.item,
                                items);
                        listView.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }

    private void init(final String key, final String value){
        new Thread(new Runnable() {
            @Override
            public void run() {
                articles = connectApi.GetApi(key,value);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        items.clear();
                        items.addAll(articles.getArticles());
                        ArrayAdapter<article> adapter = new
                                AdapterItems(
                                MainActivity.this,
                                R.layout.item,
                                items);
                        listView.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }

}
