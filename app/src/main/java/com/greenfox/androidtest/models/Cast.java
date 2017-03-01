package com.greenfox.androidtest.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cast implements Serializable {

    @SerializedName("character")
    String character;

    @SerializedName("name")
    String name;

    @SerializedName("profile_path")
    String profilePath;

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public String getProfilePath() {
        return profilePath;
    }
}
