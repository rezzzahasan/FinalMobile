package com.example.h071211010_finalmobile.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class FavoriteModel implements Parcelable {
    private final int id;
    private final String backdropUrl;
    private final String original_title;
    private final String overview;
    private final String poster_path;
    private final String release_date;
    private final String vote_average;


    public FavoriteModel(int id, String original_title, String overview, String poster_path, String release_date, String vote_average, String backdropUrl) {
        this.id = id;
        this.original_title = original_title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.backdropUrl = backdropUrl;
    }

    protected FavoriteModel(Parcel in) {
        id = in.readInt();
        backdropUrl = in.readString();
        original_title = in.readString();
        overview = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        vote_average = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(backdropUrl);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeString(vote_average);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FavoriteModel> CREATOR = new Creator<FavoriteModel>() {
        @Override
        public FavoriteModel createFromParcel(Parcel in) {
            return new FavoriteModel(in);
        }

        @Override
        public FavoriteModel[] newArray(int size) {
            return new FavoriteModel[size];
        }
    };
    public int getId() {
        return id;
    }
    public String getBackdropUrl() {
        return backdropUrl;
    }
    public String getOriginal_title() {
        return original_title;
    }
    public String getOverview() {
        return overview;
    }
    public String getPoster_path() {
        return poster_path;
    }
    public String getRelease_date() {
        return release_date;
    }
    public String getVote_average() {
        return vote_average;
    }
}


