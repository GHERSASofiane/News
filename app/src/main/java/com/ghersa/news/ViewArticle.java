package com.ghersa.news;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewArticle extends AppCompatActivity {

    private String author = "" ;
    private String titre = "" ;
    private String url = "" ;
    private String publishedAt = "" ;
    private String content = "" ;

    private TextView vwtitre, vwcontenu, vwdate, vwauthor;
    private ImageButton btn_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_article);

        final Intent intent = getIntent();
        ArrayList<String> art = intent.getStringArrayListExtra(MainActivity.EXTRA_MESSAGE);

//        author, titre, url, publishedAt, content
        if(!art.get(0).equals("null")) author = art.get(0) ;
        if(!art.get(1).equals("null")) titre = art.get(1) ;
        if(!art.get(2).equals("null")) url = art.get(2) ;
        if(!art.get(3).equals("null")) publishedAt = art.get(3).substring(0,10)+"  "+art.get(3).substring(11,16) ;
        if(!art.get(4).equals("null")) content = art.get(4) ;

        vwdate = findViewById(R.id.vwdate);
        vwauthor = findViewById(R.id.vwauthor);
        vwtitre = findViewById(R.id.titre);
        vwcontenu = findViewById(R.id.contenu);
        btn_link = findViewById(R.id.btn_link);

        vwtitre.setText(titre);
        vwcontenu.setText(content);
        vwauthor.setText(author);
        vwdate.setText(publishedAt);
        btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent( Intent.ACTION_VIEW, uri );
                startActivity(intent);
            }
        });

    }
}
