package com.ghersa.news;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE =
            "com.ltm.ltmactionbar.MESSAGE";
    private List<Article> items = new ArrayList<>();
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
    private boolean isVisibleSerch = false;


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.mnfavory :
                OpenHistory();
                break;
            case R.id.mnserch :
                isVisibleSerch = !isVisibleSerch;
                if(isVisibleSerch){research.setVisibility(View.VISIBLE);}
                else {  research.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(research.getWindowToken(), 0);
                }
                break;
            case R.id.mnsettings :
                OpenSettings();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init(){
        research.clearFocus();
        research.setVisibility(View.GONE);
        research.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String tmp = v.getText().toString();
                researchAPI("",tmp,"Keywords");
                research.clearFocus();
                isVisibleSerch = !isVisibleSerch;
                research.setVisibility(View.GONE);
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(research.getWindowToken(), 0);
                return true;
            }
        });
    }

    private void getArticles(){
        items.clear();
        researchAPI("","","Top");
        for(java.lang.Object object : prefs_Categorys) {
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
        new AsyncTask<Void,Void, Object>(){
            @Override
            protected Object doInBackground(Void... voids) {
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
            protected void onPostExecute(Object object) {
                if (object!=null)
                    setArticles(object.getArticles());
            }
        }.execute();

    }

    private void setArticles(List<Article> articles){
        items.addAll(articles);
        ArrayAdapter<Article> adapter = new
                AdapterItems(
                MainActivity.this,
                R.layout.item,
                items);
        listView.setAdapter(adapter);
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

    public void OpenHistory(){
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }
}
