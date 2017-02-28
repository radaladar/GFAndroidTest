package com.greenfox.androidtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.androidtest.models.Movie;
import com.greenfox.androidtest.network.MovieDbManager;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends Activity{

    ImageView poster;
    TextView title;
    TextView description;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        poster = (ImageView) findViewById(R.id.movieDetailPoster);
        title = (TextView) findViewById(R.id.movieDetailTitle);
        description = (TextView) findViewById(R.id.movieDetailDescriptionBody);
        movie = (Movie) getIntent().getSerializableExtra("movie");
        Picasso.with(getApplicationContext())
                .load(MovieDbManager.IMAGE_BASE_URL + movie.getPosterPath())
                .into(poster);
        title.setText(movie.getTitle());
        description.setText(movie.getOverview());
    }
}
