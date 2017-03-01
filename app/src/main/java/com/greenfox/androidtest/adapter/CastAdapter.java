package com.greenfox.androidtest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.androidtest.R;
import com.greenfox.androidtest.models.Cast;
import com.greenfox.androidtest.network.MovieDbManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CastAdapter extends ArrayAdapter<Cast> {

    public CastAdapter(Context context, List<Cast> castList) {
        super(context, 0, castList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cast cast = super.getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cast_item_layout, parent, false);
        }
        ImageView castImage = (ImageView) convertView.findViewById(R.id.castImage);
        TextView castName = (TextView) convertView.findViewById(R.id.castName);
        TextView castCharacter = (TextView) convertView.findViewById(R.id.castCharacter);
        Picasso.with(getContext())
                .load(MovieDbManager.CAST_BASE_URL + cast.getProfilePath())
                .into(castImage);
        castName.setText(String.valueOf(cast.getName()));
        castCharacter.setText(String.valueOf(cast.getCharacter()));
        return convertView;
    }
}
