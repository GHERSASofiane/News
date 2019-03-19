package com.ghersa.news;

import android.os.AsyncTask;
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

    private List<article> items = new ArrayList<>();
    private ConnectAPI connectApi = new ConnectAPI();
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
        new AsyncTask<Void,Void,object>(){
            @Override
            protected object doInBackground(Void... voids) {
                return connectApi.GetKeywordsApi(key);
            }

            @Override
            protected void onPostExecute(object object) {
                items.clear();
                setArticles(object.getArticles());
            }
        }.execute();

    }

    private void init(final String key, final String value){
        new AsyncTask<Void,Void,object>(){
            @Override
            protected object doInBackground(Void... voids) {
                return connectApi.GetApi(key,value);
            }

            @Override
            protected void onPostExecute(object object) {
                setArticles(object.getArticles());
            }
        }.execute();

    }

    private void setArticles(List<article> articles){
        items.addAll(articles);
        ArrayAdapter<article> adapter = new
                AdapterItems(
                MainActivity.this,
                R.layout.item,
                items);
        listView.setAdapter(adapter);
    }


}
