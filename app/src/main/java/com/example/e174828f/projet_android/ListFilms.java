package com.example.e174828f.projet_android;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import java.util.List;
import retrofit2.Call;


@SuppressWarnings("ALL")
public interface ListFilms {

    public static final String endpoint ="https://api.themoviedb.org/3/";


@GET("/movie/{movie_id}")
Call<List<Film>> getFilmsResults(@Path("id") int movie_id, @Query("api_key") String api_key);

}
