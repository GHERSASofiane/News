package com.ghersa.news;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private String country = "fr";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        Research = findViewById(R.id.Research);
        button_Research = findViewById(R.id.button_Research);
        init();
//      a chaque clique dans la barre de recherche on met a jour notre fil d'actuelité
        button_Research.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = Research.getText().toString();
                Research("",tmp,"Keywords");
            }
        });

    }


    public void Research(final String key, final String value, final String stat){
        new AsyncTask<Void,Void,object>(){
            @Override
            protected object doInBackground(Void... voids) {
                switch (stat){
                    case "Keywords" : items.clear();
                        return connectApi.GetKeywordsApi(value);

                    case "country" : return connectApi.Getcountry(key,value);

                    default: return connectApi.GetApi(key,value,stat);
                }

            }

            @Override
            protected void onPostExecute(object object) {
                items.clear();
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

    private void init(){
        Context context = this;
        SharedPreferences pref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences sharedPref = context.getSharedPreferences( "main",  Context.MODE_PRIVATE);

        // Write
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("country", "fr");
        editor.commit();
// Read
        this.country = sharedPref.getString("country", "NULL");

        Research("country",this.country,"country" );// Init la recherche
//        Log.i("GHERSA",label);



        // Write
        editor.putString("category", "business");
        editor.commit();
// Read
        String label = sharedPref.getString("category", "NULL");
        Research("category",label,this.country);// Init la recherche
        Log.i("GHERSA",label);
    }

}
