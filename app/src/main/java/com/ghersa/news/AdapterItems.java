package com.ghersa.news;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AdapterItems extends ArrayAdapter<article> {
    private final int layout;
    ImageButton imageButton;
    ImageView imageView;

    public AdapterItems(@NonNull Context context, int resource, @NonNull List<article> objects) {
        super(context, resource, objects);
        layout = resource;
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
        imageButton = view.findViewById(R.id.Favori);
        imageView = view.findViewById(R.id.Image);

        titleView.setText(item);
        // add image to ImageView

        try {
            Glide.with(view).load(img).into(imageView);
        }catch (Exception e){

        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(getItem(position).getUrl());
                Intent intent = new Intent( Intent.ACTION_VIEW, uri );
                getContext().startActivity(intent);

            }
        });

        return view;

    }


}
