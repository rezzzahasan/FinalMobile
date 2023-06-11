package com.example.h071211010_finalmobile.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TvShowModel implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("genre_ids")
    private final List<Integer> genreIds;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("original_name")
    private String original_name;
    @SerializedName("overview")
    private String overview;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("first_air_date")
    private String first_air_date;
    @SerializedName("vote_average")
    private String vote_average;




    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TvShowModel> CREATOR = new Creator<TvShowModel>() {
        @Override
        public TvShowModel createFromParcel(Parcel in) {
            return new TvShowModel(in);
        }

        @Override
        public TvShowModel[] newArray(int size) {
            return new TvShowModel[size];
        }
    };

    public int getId() {
        return id;
    }


    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
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

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    protected TvShowModel(Parcel in) {
        id = in.readInt();
        genreIds = new ArrayList<>();
        in.readList(genreIds, Integer.class.getClassLoader());
        backdrop_path = in.readString();
        original_name = in.readString();
        overview = in.readString();
        poster_path = in.readString();
        first_air_date = in.readString();
        vote_average = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeList(genreIds);
        dest.writeString(backdrop_path);
        dest.writeString(original_name);
        dest.writeString(overview);
        dest.writeString(poster_path);
        dest.writeString(first_air_date);
        dest.writeString(vote_average);
    }

}
