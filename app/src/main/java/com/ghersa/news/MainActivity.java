package com.ghersa.news;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE =
            "com.ltm.ltmactionbar.MESSAGE";
    private List<article> items = new ArrayList<>();
    private ConnectAPI connectApi = new ConnectAPI();
    private ListView listView;
    private TextView research;

    //    Mes Pref
    private String user_name = "New User";
    private String pref_Langue = "fr";
    private String pref_Country = "fr";
    private Set<String> prefs_Categorys = new HashSet<>();
    private String tmppref_Langue = "fr";
    private String tmppref_Country = "fr";
    private Set<String> tmpprefs_Categorys = new HashSet<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test_1_Connect();
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        research = findViewById(R.id.research);


        init();
        getArticles();

    }


    private void init(){
        research.clearFocus();
        research.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String tmp = v.getText().toString();
                researchAPI("",tmp,"Keywords");
                research.clearFocus();
                return true;
            }
        });
    }

    private void getArticles(){
        items.clear();
        researchAPI("","","Top");
        for(Object object : prefs_Categorys) {
            String element = (String) object;
            researchAPI("",element,"category");
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        tmppref_Country = pref_Country;
        tmppref_Langue = pref_Langue;
        tmpprefs_Categorys = prefs_Categorys;
    }

    @Override
    protected void onResume() {
        super.onResume();
        test_1_Connect();
        if(tmppref_Country!=pref_Country || tmppref_Langue!=pref_Langue || tmpprefs_Categorys!=prefs_Categorys){
            getArticles();
        }
    }

    public void researchAPI(final String key, final String value, final String stat){
        new AsyncTask<Void,Void,object>(){
            @Override
            protected object doInBackground(Void... voids) {
                switch (stat){
                    case "Keywords" : items.clear();
                        return connectApi.GetKeywordsApi(value);

                    case "category" :
                        return connectApi.Getcategory(MainActivity.this.pref_Country,value);
                    default:
                        return connectApi.GetTopHeadlines(MainActivity.this.pref_Country);
                }
            }

            @Override
            protected void onPostExecute(object object) {
                if (object!=null)
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

    public void openSettings(View view) {
        OpenSettings();
    }

    public void test_1_Connect(){
//        Tester si la 1 Er connexion
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        user_name = sharedPreferences.getString("user_name", "New User");
        if(user_name.equals("New User")){
            OpenSettings();
            Toast toast = Toast.makeText(MainActivity.this, "Merci de compléter le paramétrage avant la premiére utilisation. ", Toast.LENGTH_LONG);
            toast.show();

        }else{
            //    Recup mes Pref
            pref_Langue = sharedPreferences.getString("pref_Langue", "fr");
            pref_Country = sharedPreferences.getString("pref_Country", "fr");
            prefs_Categorys =  sharedPreferences.getStringSet("prefs_Categorys", new HashSet<String>());
        }
    }

    public void OpenSettings(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
