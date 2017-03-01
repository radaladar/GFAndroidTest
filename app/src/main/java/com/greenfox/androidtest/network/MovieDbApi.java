package com.greenfox.androidtest.network;

import com.greenfox.androidtest.models.CreditsResponse;
import com.greenfox.androidtest.models.GenresListResponse;
import com.greenfox.androidtest.models.LoadPopularMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDbApi {

    @GET("genre/movie/list")
    Call<GenresListResponse> getGenres();

    @GET("movie/popular")
    Call<LoadPopularMoviesResponse> getPopularMovies(@Query("page") int page);

    @GET("movie/{movie_id}/credits")
    Call<CreditsResponse> getCredits(@Path("movie_id") int movieId);
}
