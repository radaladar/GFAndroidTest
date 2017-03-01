package com.greenfox.androidtest.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CreditsResponse implements Serializable{

    @SerializedName("cast")
    List<Cast> cast;

    @SerializedName("crew")
    List<Crew> crew;

    public List<Cast> getCast() {
        return cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public Crew getDirector() {
        for (Crew member : crew) {
            if (member.getJob().equals("Director")) {
                return member;
            }
        }
        return null;
    }
}
