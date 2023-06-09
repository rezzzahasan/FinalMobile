package com.example.h071211010_finalmobile.Model;

import com.google.gson.annotations.SerializedName;

public class MovieModel {
    @SerializedName("id")
    private String id;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("original_title")
    private String original_title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("vote_average")
    private String vote_average;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

}
