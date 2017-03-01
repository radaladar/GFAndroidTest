package com.greenfox.androidtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.greenfox.androidtest.adapter.CastAdapter;
import com.greenfox.androidtest.models.Cast;
import com.greenfox.androidtest.models.CreditsResponse;
import com.greenfox.androidtest.models.Movie;
import com.greenfox.androidtest.network.MovieDbManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends Activity {

    ImageView poster;
    TextView title;
    TextView year;
    TextView description;
    TextView directorName;
    ListView castList;
    CastAdapter adapter;
    Movie movie;
    List<Cast> cast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        movie = (Movie) getIntent().getSerializableExtra("movie");
        poster = (ImageView) findViewById(R.id.movieDetailPoster);
        title = (TextView) findViewById(R.id.movieDetailTitle);
        year = (TextView) findViewById(R.id.movieDetailYear);
        description = (TextView) findViewById(R.id.movieDetailDescriptionBody);
        directorName = (TextView) findViewById(R.id.movieDetailDirector);
        castList = (ListView) findViewById(R.id.movieDetailCast);
        Picasso.with(getApplicationContext())
                .load(MovieDbManager.BACKDROP_BASE_URL + movie.getBackdropPath())
                .into(poster);
        title.setText(movie.getTitle());
        year.setText("(" + movie.getReleaseDateText().substring(0,4) + ")");
        description.setText(movie.getOverview());

        MovieDbManager.getInstance().loadCredits(movie.getId(), new Callback<CreditsResponse>() {
            @Override
            public void onResponse(Call<CreditsResponse> call, Response<CreditsResponse> response) {
                directorName.setText(response.body().getDirector().getName());
                cast = response.body().getCast();
                adapter = new CastAdapter(getApplicationContext(),cast.subList(0,5));
                castList.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<CreditsResponse> call, Throwable t) {

            }
        });

        poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poster.setVisibility(View.GONE);
            }
        });
    }
}
