package com.example.e174828f.projet_android;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageFilms {
    @SerializedName("posters")
    private List<ImageFilm> result;



    @Override
    public String toString() {
        return "Films{" +
                "result=" + result +
                '}';
    }

   public List<ImageFilm> getAllElements(){return result;}
}
