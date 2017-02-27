package com.greenfox.androidtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.androidtest.R;
import com.greenfox.androidtest.models.Genre;
import com.greenfox.androidtest.models.Movie;
import com.greenfox.androidtest.network.MovieDbManager;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    List<Movie> movieList;
    HashMap<Integer, String> genreMap;
    Context context;


    public MovieAdapter(List<Movie> movieList, List<Genre> genreList, Context context) {
        this.movieList = movieList;
        this.context = context;
        genreMap = new HashMap<>();
        for (Genre genre : genreList) {
            genreMap.put(genre.getId(), genre.getName());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        Picasso.with(context)
                .load(MovieDbManager.IMAGE_BASE_URL + movie.getPosterPath())
                .into(holder.poster);
        holder.title.setText(movie.getTitle());
        holder.rating.setText(Float.toString(movie.getAverageVote()));
        holder.genre.setText(getGenresAsString(movie));
        holder.year.setText(movie.getReleaseDateText().substring(0,4));
        holder.description.setText(movie.getOverview());

    }

    public String getGenresAsString(Movie movie) {
        StringBuilder genresString = new StringBuilder("");
        for (int id : movie.getGenreIdList()) {
            genresString.append(genreMap.get(id));
            if (movie.getGenreIdList().indexOf(id) < movie.getGenreIdList().size()-1) {
                genresString.append(", ");
            }
        }
        return genresString.toString();
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView poster;
        TextView title;
        TextView rating;
        TextView genre;
        TextView year;
        TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.moviePoster);
            title = (TextView) itemView.findViewById(R.id.movieTitle);
            rating = (TextView) itemView.findViewById(R.id.rating);
            genre = (TextView) itemView.findViewById(R.id.genre);
            year = (TextView) itemView.findViewById(R.id.year);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
