package com.iktwo.popularmovies;

public class Urls {
    public static final String DISCOVER_URL = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=" + Keys.THE_MOVIE_DB_API_KEY;
    /// TODO: this is wrong, according to docs this could change, so we need to query api to get this url and cache it
    public static final String IMAGES_URL = "http://image.tmdb.org/t/p/w780/";
}
