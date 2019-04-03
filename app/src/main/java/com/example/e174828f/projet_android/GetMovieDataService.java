package com.example.e174828f.projet_android;

import com.example.e174828f.projet_android.Genre;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

@SuppressWarnings("ALL")
public interface GetMovieDataService {

    String BASE_URL = "https://api.themoviedb.org/3/";

    @GET("genre/movie/list?api_key=6f24e995a9146dc661b833c2a79481b5&language=fr-fr")
    Call<ListGenre> getGenre();

    @GET("search/company")
    Call<ListCompany> getCompany(@QueryMap Map<String, String> string);

    @GET("movie/{movie_id}?api_key=6f24e995a9146dc661b833c2a79481b5")
    Call<Film> getFilmsResults(@Path("movie_id") int movie_id);

    @GET("discover/movie?api_key=6f24e995a9146dc661b833c2a79481b5")
    Call<Films> getFilms(@Query("with_companies") int idCompanies , @Query("with_genres") int idGenre,
                         @Query("year") int year);
    @GET("movie/popular?api_key=6f24e995a9146dc661b833c2a79481b5")
    Call<Films> getPopularFilms();

    @GET("movie/top_rated?api_key=6f24e995a9146dc661b833c2a79481b5")
    Call<Films> getTopRatedFilms();

    @GET("movie/upcoming?api_key=6f24e995a9146dc661b833c2a79481b5")
    Call<Films> getUpcommingFilms();

    @GET("search/movie?api_key=6f24e995a9146dc661b833c2a79481b5")
    Call<Films> getMovieByName(@Query("query") String query);


}
