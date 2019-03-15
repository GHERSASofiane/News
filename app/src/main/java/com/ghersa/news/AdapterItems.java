package com.ghersa.news;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class AdapterItems extends ArrayAdapter<String> {
    private final int layout;
    ImageButton imageButton;

    public AdapterItems(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null){
            // Create new view
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(layout, null);
        } else {
            view = convertView;
        }

        String item = getItem(position);

        // Recycle view
        TextView titleView = view.findViewById(R.id.Titre);

        imageButton = view.findViewById(R.id.Favori);
        titleView.setText(item);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("GHERSA","SALUTTTTT " + position + " :: " + getItem(position));
            }
        });

        return view;

    }


}
