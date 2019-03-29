package com.example.e174828f.projet_android;

import com.example.e174828f.projet_android.Genre;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

@SuppressWarnings("ALL")
public interface GetMovieDataService {

    String BASE_URL = "https://api.themoviedb.org/3/genre/movie/";

    @GET("list?api_key=6f24e995a9146dc661b833c2a79481b5")
    Call<ListGenre> getGenre();

}
