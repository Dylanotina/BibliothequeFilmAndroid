package com.example.e174828f.projet_android;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import java.util.List;
import retrofit2.Call;


@SuppressWarnings("ALL")
public interface ListFilms {

    public static final String endpoint ="https://api.themoviedb.org/3/";



@GET("movie/{movie_id}?api_key=6f24e995a9146dc661b833c2a79481b5")
Call<Films> getFilmsResults(@Path("id") int movie_id);

@GET("discover/movie?api_key=6f24e995a9146dc661b833c2a79481b5")
Call<Films> getFilms();



}
