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

    final List<String> items = new ArrayList<>();
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




            ListView lv = findViewById(R.id.listView);

            items.add("Item 1");
            items.add("Item 2");
            items.add("Item 3");
            items.add("Item 4");
            items.add("Item 5");
            items.add("Item 6");
            items.add("Item 7");
            items.add("Item 8");
            items.add("Item 9");
            items.add("Item 10");
            items.add("Item 11");
            items.add("Item 12");
            items.add("Item 13");
            items.add("Item 14");
            items.add("Item 7");
            items.add("Item 8");
            items.add("Item 9");
            items.add("Item 10");
            items.add("Item 11");
            items.add("Item 12");
            items.add("Item 13");
            items.add("Item 14");

            final ArrayAdapter<String> adapter = new

                    AdapterItems(
                    this,
                    R.layout.item,
                    items);

            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i("GHERSA","SALUTTTTT :: "+ position);
                }
            });


    }

    public void onResum(View vv){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnect = null;
                try {
                    URL url = new URL("https://newsapi.org/v2/top-headlines?country=fr&apiKey=fb82207d6c214614bc18937bb5e0f4f3&pageSize=100");
                    urlConnect = (HttpURLConnection) url.openConnection();
                    urlConnect.setRequestMethod("GET");

                    InputStream in = new BufferedInputStream(urlConnect.getInputStream());
                    Scanner scanner = new Scanner(in);
                    final object obj = new Genson().deserialize(scanner.nextLine(), object.class);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i=0; i<obj.getArticles().size(); i++){

                                Log.i("Exception : "," : "+ obj.getArticles().get(i).toString()  );
                            }
                        }
                    });


                    in.close();
                }catch (Exception e){
                    Log.i("Exception : ","Cannot fond HTTP "+e);
                }finally {
                    if(urlConnect != null) urlConnect.disconnect();
                }
            }
        }).start();
    }

}
