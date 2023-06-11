package com.example.h071211010_finalmobile.Response;

import com.example.h071211010_finalmobile.Model.TvShowModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowResponse {
    @SerializedName("results")
    private List<TvShowModel> results;

    public List<TvShowModel> getResults() {
        return results;
    }
}
