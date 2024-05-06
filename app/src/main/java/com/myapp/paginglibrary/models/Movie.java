package com.myapp.paginglibrary.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("poster_path")
    @Expose
    private String poster_Path;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("vote_average")
    @Expose
    private double voteAverage;

    public String getPoster_Path() {
        return poster_Path;
    }

    public void setPoster_Path(String poster_Path) {
        this.poster_Path = poster_Path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVote_average() {
        return voteAverage;
    }

    public void setVote_average(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        return false;
    }

}
