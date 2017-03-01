package com.greenfox.androidtest.network;

import com.greenfox.androidtest.models.CreditsResponse;
import com.greenfox.androidtest.models.GenresListResponse;
import com.greenfox.androidtest.models.LoadPopularMoviesResponse;

import retrofit2.Callback;
import retrofit2.Retrofit;

public class MovieDbManager {
    //You can use this base url to load images with 342 pixel width
    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w342/";
    public static final String BACKDROP_BASE_URL = "http://image.tmdb.org/t/p/w1280/";
    public static final String CAST_BASE_URL = "http://image.tmdb.org/t/p/w185/";


    //region singleton
    private static MovieDbManager instance;

    public static MovieDbManager getInstance() {
        if(instance == null) {
            instance = new MovieDbManager();
        }

        return instance;
    }
    //endregion

    private Retrofit retrofit;
    private MovieDbApi movieDbApi;

    private MovieDbManager(){
        retrofit = RetrofitHelper.initRetrofit();
        movieDbApi = retrofit.create(MovieDbApi.class);
    }

    public void loadGenres(Callback<GenresListResponse> callback) {
        movieDbApi.getGenres().enqueue(callback);
    }

    public void loadPopularMovies(int page, Callback<LoadPopularMoviesResponse> callback) {
        movieDbApi.getPopularMovies(page).enqueue(callback);
    }

    public void loadCredits(int movieId, Callback<CreditsResponse> callback) {
        movieDbApi.getCredits(movieId).enqueue(callback);
    }
}
