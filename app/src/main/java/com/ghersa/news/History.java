package com.ghersa.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class History extends AppCompatActivity {
    private List<Article> items = new ArrayList<>();
    private ListView listView;
    private DataBase dataBase ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = findViewById(R.id.listView);
        dataBase = new DataBase(this);
        setArticles(dataBase.getNews());
        dataBase.close();

    }

    private void setArticles(Vector<Article> articles){
        items.addAll(articles);
        ArrayAdapter<Article> adapter = new
                AdapterItemsHistory(
                History.this,
                R.layout.item,
                items);
        listView.setAdapter(adapter);
    }
}
