package com.greenfox.androidtest.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GenresListResponse implements Serializable {

    @SerializedName("genres")
    List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }
}
