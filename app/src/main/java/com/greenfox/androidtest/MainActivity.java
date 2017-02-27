package com.greenfox.androidtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.greenfox.androidtest.adapter.MovieAdapter;
import com.greenfox.androidtest.models.Genre;
import com.greenfox.androidtest.models.GenresListResponse;
import com.greenfox.androidtest.models.LoadPopularMoviesResponse;
import com.greenfox.androidtest.network.MovieDbManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    List<Genre> genreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MovieDbManager.getInstance().loadGenres(new Callback<GenresListResponse>() {
            @Override
            public void onResponse(Call<GenresListResponse> call, Response<GenresListResponse> response) {
                genreList = response.body().getGenres();
            }

            @Override
            public void onFailure(Call<GenresListResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        MovieDbManager.getInstance().loadPopularMovies(1, new Callback<LoadPopularMoviesResponse>() {
            @Override
            public void onResponse(Call<LoadPopularMoviesResponse> call, Response<LoadPopularMoviesResponse> response) {
                movieAdapter = new MovieAdapter(response.body().getMovies(), genreList, getApplicationContext());
                recyclerView.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<LoadPopularMoviesResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
