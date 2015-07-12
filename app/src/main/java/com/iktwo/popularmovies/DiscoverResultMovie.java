package com.iktwo.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class DiscoverResultMovie implements Parcelable {
    public static final Creator<DiscoverResultMovie> CREATOR = new Creator<DiscoverResultMovie>() {
        public DiscoverResultMovie createFromParcel(Parcel source) {
            return new DiscoverResultMovie(source);
        }

        public DiscoverResultMovie[] newArray(int size) {
            return new DiscoverResultMovie[size];
        }
    };
    public boolean adult;
    public String backdrop_path;
    public List<Integer> genre_ids = new ArrayList<>();
    public double id;
    public String original_language;
    public String original_title;
    public String overview;
    public String poster_path;
    public String release_date;
    public String title;
    public boolean video;
    public double vote_average;
    public double vote_count;

    protected DiscoverResultMovie(Parcel in) {
        this.adult = in.readByte() != 0;
        this.backdrop_path = in.readString();
        this.genre_ids = new ArrayList<Integer>();
        in.readList(this.genre_ids, List.class.getClassLoader());
        this.id = in.readDouble();
        this.original_language = in.readString();
        this.original_title = in.readString();
        this.overview = in.readString();
        this.poster_path = in.readString();
        this.release_date = in.readString();
        this.title = in.readString();
        this.video = in.readByte() != 0;
        this.vote_average = in.readDouble();
        this.vote_count = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.backdrop_path);
        dest.writeList(this.genre_ids);
        dest.writeDouble(this.id);
        dest.writeString(this.original_language);
        dest.writeString(this.original_title);
        dest.writeString(this.overview);
        dest.writeString(this.poster_path);
        dest.writeString(this.release_date);
        dest.writeString(this.title);
        dest.writeByte(video ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.vote_average);
        dest.writeDouble(this.vote_count);
    }
}
