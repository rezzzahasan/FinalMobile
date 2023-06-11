package com.example.h071211010_finalmobile.Response;

import com.example.h071211010_finalmobile.Model.MovieModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("results")
    private List<MovieModel> results;

    public List<MovieModel> getData() {
        return results;
    }
}
