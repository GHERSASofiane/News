package com.ghersa.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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
}
