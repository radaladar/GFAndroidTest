package com.greenfox.androidtest.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Crew implements Serializable {

    @SerializedName("job")
    String job;

    @SerializedName("name")
    String name;

    public String getJob() {
        return job;
    }

    public String getName() {
        return name;
    }
}
