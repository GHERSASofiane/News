package com.ghersa.news;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdapterItems extends ArrayAdapter<Article> {
    private final int layout;
    ImageButton btn_fav;
    ImageButton btn_view;
    ImageButton btn_link;
    ImageView imageView;
    LinearLayout lytSp;
    private DataBase dataBase ;
    private Speech speech;
    private String pref_Langue;

    public AdapterItems(@NonNull Context context, int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
        layout = resource;
        speech = new Speech(context);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final View view;
        if (convertView == null){
            // Create new view
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(layout, null);
        } else {
            view = convertView;
        }

        String item = getItem(position).getTitle();
        String img = getItem(position).getUrlToImage();
        // Recycle view
        TextView titleView = view.findViewById(R.id.Titre);
        btn_link = view.findViewById(R.id.btn_link);
        btn_view = view.findViewById(R.id.btn_view);
        btn_fav = view.findViewById(R.id.btn_fav);
        imageView = view.findViewById(R.id.Image);
        lytSp = view.findViewById(R.id.lytSpeech);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        pref_Langue = sharedPreferences.getString("pref_Langue", "fr");

        titleView.setText(item);
        // add image to ImageView

        try {
            Glide.with(view).load(img).into(imageView);
        }catch (Exception e){

        }

        btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(getItem(position).getUrl());
                Intent intent = new Intent( Intent.ACTION_VIEW, uri );
                getContext().startActivity(intent);
            }
        });

        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBase = new DataBase(view.getContext());
                dataBase.insertNews(getItem(position));
                remove(getItem(position));
                dataBase.close();
            }
        });

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Article art = getItem(position);
                ArrayList<String> parm = new ArrayList<>();

                if(art.getAuthor()==null)
                    parm.add("null");
                else if(art.getAuthor().equals(""))
                    parm.add("null");
                else
                    parm.add(art.getAuthor());
//
                if(art.getTitle()==null)
                    parm.add("null");
                else if(art.getTitle().equals(""))
                    parm.add("null");
                else
                    parm.add(art.getTitle());
//
                if(art.getUrl()==null)
                    parm.add("null");
                else if(art.getUrl().equals(""))
                    parm.add("null");
                else
                    parm.add(art.getUrl());
//
                if(art.getPublishedAt()==null)
                    parm.add("null");
                else if(art.getPublishedAt().equals(""))
                    parm.add("null");
                else
                    parm.add(art.getPublishedAt());
//
                if(art.getContent()==null)
                    parm.add("null");
                else if(art.getContent().equals(""))
                    parm.add("null");
                else
                    parm.add(art.getContent());


                Intent i1 = new Intent( view.getContext() , ViewArticle.class);
                i1.putStringArrayListExtra(MainActivity.EXTRA_MESSAGE,parm);
                view.getContext().startActivity(i1);
            }
        });

        lytSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Article art = getItem(position);
                String res = "Titre "+art.getTitle()+" Description "+art.getDescription();

                speech.speak( res );
            }
        });

        return view;

    }



}
